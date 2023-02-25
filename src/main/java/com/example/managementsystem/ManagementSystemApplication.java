package com.example.managementsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.example.managementsystem.Dao")
@SpringBootApplication
public class ManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementSystemApplication.class, args);
    }

}
