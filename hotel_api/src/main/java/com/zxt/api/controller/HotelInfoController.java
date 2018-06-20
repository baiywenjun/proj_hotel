package com.zxt.api.controller;

import com.zxt.common.result.Rt;
import com.zxt.common.util.MathUtil;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.pojo.HotelInfoQuery;
import com.zxt.hotel.service.HotelInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


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
     * 所有酒店，不含距离
     * @param hotelName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "酒店列表，不含距离")
    @ApiImplicitParam(name = "hotelName", value = "酒店名称",  dataType = "String")
    public Rt queryHotelIntoList(String hotelName, Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelInfoQuery query = new HotelInfoQuery();
        query.setName(hotelName);
        return hotelInfoService.queryHotelInfoByPage(query, handle.getPage(), handle.getLimit());
    }

    /**
     * 所有酒店
     * @param hotelName
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "酒店列表" , notes = "包含与当前位置的距离，当前位置坐标必填")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lng", value = "经度", required = true, dataType = "String"),
            @ApiImplicitParam(name = "lat", value = "纬度", required = true, dataType = "String"),
            @ApiImplicitParam(name = "hotelName", value = "酒店名称",  dataType = "String")
    })
    @RequestMapping("/lists")
    public Rt queryHotelInfoListWithDistance(String lng, String lat, String hotelName, Integer page, Integer limit){
        if(StringUtils.isEmpty(lng) || StringUtils.isEmpty(lat)){
            return Rt.error(403,"经纬度不能为空");
        }
        if(!MathUtil.isDouble(lng) && !MathUtil.isDouble(lat)){
            return Rt.error(403,"经纬度格式不正确");
        }
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelInfoQuery query = new HotelInfoQuery();
        query.setName(hotelName);
        query.setLng(lng);
        query.setLat(lat);
        //return hotelInfoService.queryHotelInfoFullByPage(query, handle.getPage(), handle.getLimit());
        return hotelInfoService.queryHotelInfoFullDistanceByPageOver(query,handle.getPage(),handle.getLimit());
        //return hotelInfoService.queryHotelInfoFullByPageDiscard(query,handle.getPage(),handle.getLimit());
    }

}
