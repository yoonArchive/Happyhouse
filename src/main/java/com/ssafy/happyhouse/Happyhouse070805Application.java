package com.ssafy.happyhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(basePackages = { "com.ssafy.happyhouse.model.mapper" })
public class Happyhouse070805Application {

	public static void main(String[] args) {
		SpringApplication.run(Happyhouse070805Application.class, args);
	}

}
