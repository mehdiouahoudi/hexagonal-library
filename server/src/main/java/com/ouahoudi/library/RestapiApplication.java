package com.ouahoudi.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.ouahoudi.library",
        "com.ouahoudi.library.infrastructure",
        "com.ouahoudi.library.web",
        "com.ouahoudi.library.application"
})
@EnableJpaRepositories()
public class RestapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestapiApplication.class, args);
    }
}
