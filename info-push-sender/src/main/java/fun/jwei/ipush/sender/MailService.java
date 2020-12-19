package fun.jwei.ipush.sender;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/**
 * 邮件发送
 *
 * @author jwei
 * @date 2020/11/15
 */
@Slf4j
@Service
public class MailService {

    @Value("${spring.mail.nickname}")
    private String nickname;
    @Value("${spring.mail.username}")
    private String from;

    private JavaMailSender mailSender;
    private Configuration configuration;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }


    public void sendText(String subject, String text, String... to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(nickname + "<" + from + ">");
//        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendMime(String subject, String text, String... to) throws MessagingException, URISyntaxException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        try {
            mimeMessageHelper.setFrom(from, "jwei");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text + "<img src=\"cid:test.jpg\">", true);
        URL url = this.getClass().getClassLoader().getResource("static/img/test.jpg");
        mimeMessageHelper.addInline("test.jpg",new File(url.toURI()));
        mailSender.send(mimeMessage);
    }

    @Async
    public void sendHtml(String subject, String html, Map<String,Object> model, String... to) throws MessagingException, IOException, TemplateException {
        log.info(Thread.currentThread().getName() + "______开始发送邮件");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        try {
            mimeMessageHelper.setFrom(from, nickname);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Template template = configuration.getTemplate(html);
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        mailSender.send(mimeMessage);
        log.info(Thread.currentThread().getName() + "______发送邮件完成");
    }
}
