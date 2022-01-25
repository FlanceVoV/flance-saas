package com.flance.saas.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/nau/test")
public class Test2Controller {

    @GetMapping("/test")
    public String test() {
        log.info("测试测试");
        return "nau-test";
    }

}
