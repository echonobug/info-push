package ipush.util;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 加密解密测试
 *
 * @author jwei
 * @date 2020/11/16
 */
@Slf4j
@SpringBootTest
public class EncryptorTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void encode() {
        String encrypt = stringEncryptor.encrypt("info-push");
        log.info(encrypt);
    }

    @Test
    public void decode() {
        String decrypt = stringEncryptor.decrypt("WkINcAMIjjqTN8Wi+CWxQqJ4C2iyZpUY");
        log.info(decrypt);
    }
}
