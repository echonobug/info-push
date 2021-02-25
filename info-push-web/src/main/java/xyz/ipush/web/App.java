package xyz.ipush.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication(scanBasePackages = "xyz.ipush.*")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
