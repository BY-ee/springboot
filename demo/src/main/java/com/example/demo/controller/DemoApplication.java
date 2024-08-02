package com.example.demo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 주요 애노테이션: 스프링 부트 애플리케이션을 구성
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// 컨트롤러 메소드: HTTP 요청을 처리하고 응답을 반환
	@Controller
	public class HelloController {
		@GetMapping("/Hello")
		public String Hello(Model model) {
			model.addAttribute("message", "Hello, Spring Boot!");
			return "Hello";
		}
	}
}
