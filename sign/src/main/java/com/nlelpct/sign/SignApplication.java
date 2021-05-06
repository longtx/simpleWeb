package com.nlelpct.sign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan({"com.nlelpct.sign.mapper"})
@EnableSwagger2
public class SignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignApplication.class, args);
    }

}
