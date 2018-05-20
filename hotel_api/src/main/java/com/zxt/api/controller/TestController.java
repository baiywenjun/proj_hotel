package com.zxt.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/17 11:43
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/")
    public String test(){
        return "hello world";
    }
}
