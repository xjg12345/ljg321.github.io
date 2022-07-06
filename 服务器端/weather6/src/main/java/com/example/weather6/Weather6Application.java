package com.example.weather6;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.weather6.mapper")
public class Weather6Application {

    public static void main(String[] args) {
        SpringApplication.run(Weather6Application.class, args);
        System.out.println("服务器端启动成功");
    }

}
