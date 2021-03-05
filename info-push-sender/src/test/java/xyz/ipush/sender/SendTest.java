package xyz.ipush.sender;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ipush.common.dto.EmailHtmlDTO;
import xyz.ipush.sender.task.SenderTask;

import java.util.Collections;
import java.util.HashMap;

/**
 * send test
 *
 * @author jwei
 * @date 2021/3/5
 */
@SpringBootTest
public class SendTest {
    @Autowired
    private SenderTask senderTask;

    @Test
    public void sendInfo(){
        EmailHtmlDTO emailHtmlDTO = new EmailHtmlDTO();
        emailHtmlDTO.setSubject("hello");
        emailHtmlDTO.setTo(Collections.singletonList("578796735@qq.com"));
        HashMap<String, Object> model = new HashMap<>();
        model.put("name","heiehi");
        emailHtmlDTO.setModel(model);
        emailHtmlDTO.setHtml("你好！${name}");
        senderTask.sendInfoEmail(JSON.toJSONString(emailHtmlDTO));
    }
}
