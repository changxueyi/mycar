package com.cxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cxy.dao")
public class MycarApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycarApplication.class, args);
    }

}
