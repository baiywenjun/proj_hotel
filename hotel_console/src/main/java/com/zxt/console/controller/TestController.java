package com.zxt.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/18 14:27
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/index")
    public String hello(){
        return "index";
    }
}
