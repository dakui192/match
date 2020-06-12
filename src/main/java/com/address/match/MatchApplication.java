package com.address.match;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.address.match.demo.mapper")
public class MatchApplication{

    public static void main(String[] args) {
        SpringApplication.run(MatchApplication.class, args);
    }


}
