package xyz.ipush.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * sender 启动类
 *
 * @author jwei
 * @date 2021/3/4
 */
@SpringBootApplication(scanBasePackages = "xyz.ipush.*")
public class Sender {
    public static void main(String[] args) {
        SpringApplication.run(Sender.class, args);
    }
}
