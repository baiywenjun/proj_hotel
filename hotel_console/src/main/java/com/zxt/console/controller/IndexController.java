package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.hotel.pojo.PageDataVo;
import com.zxt.hotel.service.PageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/6/11 9:41
 */
@Controller
public class IndexController {


    @Autowired
    private PageDataService pageDataService;


    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "/page-data")
    @ResponseBody
    public ModelAndView indexPage(){
        PageDataVo pageDataVo = pageDataService.selectPageDataVo();
        Map map=new HashMap();
        map.put("pagedata",pageDataVo);
        return new ModelAndView("page-data",map);
    }

}
