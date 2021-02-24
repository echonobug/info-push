package xyz.ipush.sender;

import com.alibaba.fastjson.JSONObject;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import xyz.ipush.message.KafkaProducer;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * 消费者
 *
 * @author jwei
 * @date 2021/1/29
 */
@Component
@Slf4j
public class KafkaConsumer {
    @Autowired
    private MailService mailService;
    @KafkaListener(topics = KafkaProducer.TOPIC_EMAIL_ACTIVE)
    public void processMessage(String content) {
        log.info("收到一条消息====="+content);
        try {
            mailService.sendHtml("欢迎注册！","active.html", JSONObject.parseObject(content),"578796735@qq.com");
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
