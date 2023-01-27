package com.dango.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class ReggieTakeoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReggieTakeoutApplication.class, args);
		log.info("项目启动成功");
	}

}
