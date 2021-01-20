package com.bumblebee.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bumblebee.users")
public class BumblebeeUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BumblebeeUserServiceApplication.class, args);
    }

}
