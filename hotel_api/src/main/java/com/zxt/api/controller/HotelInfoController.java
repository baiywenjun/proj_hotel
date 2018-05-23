package com.zxt.api.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.pojo.HotelInfoQuery;
import com.zxt.hotel.service.HotelInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Title: 酒店相关
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/18 16:34
 */
@RestController
@RequestMapping("/hotel/info")
@Api(tags = "酒店信息")
public class HotelInfoController {

    @Autowired
    private HotelInfoService hotelInfoService;

    /**
     * 所有酒店
     * @param hotelName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "酒店列表" , notes = "详细文档...")
    @ApiImplicitParam(name = "hotelName", value = "酒店名称",  dataType = "String")
    public Rt queryHotelIntoList(String hotelName, Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelInfoQuery query = new HotelInfoQuery();
        query.setName(hotelName);
        return hotelInfoService.queryHotelInfoByPage(query, handle.getPage(), handle.getLimit());
    }



}
