package com.zxt.console.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/18 14:27
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/")
    public String hello(){
        return "hello world";
    }
}
