package fun.jwei.ipush.web;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class AppTests {

    @Autowired
    StringEncryptor stringEncryptor;


    @Test
    public void encode() {
        String encrypt = stringEncryptor.encrypt("porgookpewzscbec");
        log.info(encrypt);
    }

    @Test
    public  void decode(){
        String decrypt = stringEncryptor.decrypt("ICvZIcAdYr6+c9FfYMGY3w==");
        log.info(decrypt);
    }

}
