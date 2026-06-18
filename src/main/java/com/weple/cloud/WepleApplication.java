package com.weple.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.weple.cloud.**.mapper")
@SpringBootApplication
public class WepleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WepleApplication.class, args);
	}

}
