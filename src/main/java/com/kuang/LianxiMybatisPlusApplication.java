package com.kuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描mapper文件夹
@MapperScan("com.kuang.mapper")
public class LianxiMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(LianxiMybatisPlusApplication.class, args);
    }

}
