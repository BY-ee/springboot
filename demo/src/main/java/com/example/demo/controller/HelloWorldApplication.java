package com.example.demo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HelloWorldApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }

    // 컨트롤러 정의
    @RestController
    public class HelloWorldController {
        @GetMapping("/Hi")
        public String helloWorld() {
            return "Hello World!";
        }
    }
}
