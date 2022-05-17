package com.flance.saas.api;

import cn.hutool.core.util.ReUtil;
import org.checkerframework.checker.regex.RegexUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.flance"})
@MapperScan("com.flance.*.*.domain.*.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        String reg = "[0-9A-Z]{18}";
        String test = "11111111111111111A";
        System.out.println(ReUtil.contains(reg, test));

//        SpringApplication.run(DemoApplication.class, args);
    }

}
