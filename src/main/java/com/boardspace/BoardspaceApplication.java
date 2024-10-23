package com.boardspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BoardspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardspaceApplication.class, args);
	}

}
