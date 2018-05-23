package com.zxt.api.controller;

import com.zxt.common.exception.RRException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @RequestMapping("/hello")
    public String test2(String id){
        if(StringUtils.isEmpty(id)){
            throw new RRException("hello");
        }
        return "ok";
    }
}
