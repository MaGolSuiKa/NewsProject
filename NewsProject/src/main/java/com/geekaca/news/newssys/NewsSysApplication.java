package com.geekaca.news.newssys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.geekaca.news.newssys.dao")
public class NewsSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsSysApplication.class, args);
    }

}
