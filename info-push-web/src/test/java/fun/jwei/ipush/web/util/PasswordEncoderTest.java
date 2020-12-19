package fun.jwei.ipush.web.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 生成加密后密码
 *
 * @author jwei
 * @date 2020/12/17
 */
@SpringBootTest
@Slf4j
public class PasswordEncoderTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void encoder() {
        String encode = passwordEncoder.encode("123456");
        log.info(encode);
    }
}
