package xyz.ipush.sender.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import xyz.ipush.common.dto.EmailHtmlDTO;
import xyz.ipush.message.IPushKafkaProducer;
import xyz.ipush.sender.service.MailService;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;

/**
 * 消息发送任务
 *
 * @author jwei
 * @date 2021/1/29
 */
@Component
@Slf4j
public class SenderTask {
    @Resource
    private MailService mailService;

    @KafkaListener(topics = IPushKafkaProducer.TOPIC_EMAIL_ACTIVE)
    public void sendActiveEmail(String content) {
        try {
            mailService.sendHtml("欢迎注册！", "active.html", JSONObject.parseObject(content), "578796735@qq.com");
        } catch (MessagingException | IOException | TemplateException e) {
            log.error(e.getMessage());
        }
    }

    @KafkaListener(topics = IPushKafkaProducer.TOPIC_EMAIL_INFO)
    public void sendInfoEmail(String content) {
        EmailHtmlDTO emailHtmlDTO = JSON.parseObject(content, EmailHtmlDTO.class);
        try {
            mailService.sendRawHtml(emailHtmlDTO.getSubject(), emailHtmlDTO.getHtml(), emailHtmlDTO.getModel(), emailHtmlDTO.getTo().toArray(String[]::new));
        } catch (MessagingException | IOException | TemplateException e) {
            log.error(e.getMessage());
        }
    }
}
