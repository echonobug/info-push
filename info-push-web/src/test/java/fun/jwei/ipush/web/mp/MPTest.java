package fun.jwei.ipush.web.mp;

import fun.jwei.ipush.web.entity.AccessLog;
import fun.jwei.ipush.web.entity.User;
import fun.jwei.ipush.web.mapper.AccessLogMapper;
import fun.jwei.ipush.web.mapper.UserMapper;
import fun.jwei.ipush.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * mybatis plus 测试
 *
 * @author jwei
 * @date 2020/12/16
 */
@Slf4j
@SpringBootTest
public class MPTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccessLogMapper accessLogMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setId("admin");
        user.setName("admin");
        user.setEnable(1);
        user.setPassword("$2a$10$U1bGnXrcAxJUderOeIKkpupK1q/k1XX0kuRND7tcEK7NLm/1qKGmu");
        userMapper.insert(user);
    }

    @Test
    public void insertSelective() {
        AccessLog accessLog = new AccessLog();
        accessLog.setUserName("sss");
        accessLog.setRequestMethod("");
        accessLog.setRequestUrl("");
        accessLog.setRequestDuration(0L);
        accessLog.setResponseStatus(0);
        accessLogMapper.insertSelective(accessLog);

    }

    @Test
    public void query(){
        User user = userService.query().eq("name", "test-mp-insert3").one();
        System.out.println(user);
    }


}
