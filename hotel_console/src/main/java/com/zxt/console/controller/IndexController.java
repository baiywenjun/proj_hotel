package com.zxt.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/6/11 9:41
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/welcome")
    public String welcome(){
        return "welcome";
    }
}
