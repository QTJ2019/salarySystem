package com.scau;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.scau.*.*.mapper")
public class SalarySystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalarySystemApplication.class,args);
    }
}
