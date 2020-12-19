package fun.jwei.ipush.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"fun.jwei"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
