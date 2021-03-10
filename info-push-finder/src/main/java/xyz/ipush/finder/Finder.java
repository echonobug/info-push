package xyz.ipush.finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author jwei
 * @date 2021/3/2
 */
@SpringBootApplication(scanBasePackages = "xyz.ipush.*")
public class  Finder {
    public static void main(String[] args) {
        SpringApplication.run(Finder.class, args);
    }
}
