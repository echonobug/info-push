package fun.jwei.ipush.web.send;

import freemarker.template.TemplateException;
import fun.jwei.ipush.sender.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * mail send tests
 *
 * @author jwei
 * @date 2020/12/19
 */
@Slf4j
@SpringBootTest
public class MailTests {
    @Autowired
    private MailService mailService;

    @Test
    void sendMime() throws MessagingException, URISyntaxException {
        mailService.sendMime("测试", "测试MIME", "578796735@qq.com");
    }

    @Test
    void sendHtml() throws MessagingException, IOException, TemplateException, InterruptedException {
        Map<String, Object> model = new HashMap<>();
        model.put("name", "张三");
        model.put("sno", "2017XXX");
        log.info("开始发送...");
        long begin = System.currentTimeMillis();
        mailService.sendHtml("测试", "hello.html", model, "578796735@qq.com");
        log.info("发送成功！，耗时：{}", System.currentTimeMillis() - begin);
        Thread.sleep(15000);
    }
}
