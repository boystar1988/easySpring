package com.jianzhong.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jianzhong.demo.repository")
public class DemoApplication {

    public static void main(String[] args)
    {
        SpringApplication springApplication = new SpringApplication();
        springApplication.addListeners();
        SpringApplication.run(DemoApplication.class, args);
    }

}
