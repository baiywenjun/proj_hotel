package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.console.pojo.HotelRoomTypeDTO;
import com.zxt.hotel.pojo.HotelRoomTypeFullVO;
import com.zxt.hotel.pojo.HotelRoomTypeQuery;
import com.zxt.hotel.service.HotelRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title: 房型
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/18 17:53
 */
@Controller
@RequestMapping("/hotel/type")
public class HotelTypeController {

    @Autowired
    private HotelRoomTypeService hotelRoomTypeService;

    @RequestMapping("/page")
    public String hotelRoomTypePage(){
        return "components/hotel-type";
    }

    /**
     * 房型列表-简要
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Rt queryHotelTypeList(Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomTypeQuery query = new HotelRoomTypeQuery();
        return hotelRoomTypeService.queryHotelRoomTypeByPage(query, handle.getPage(),handle.getLimit());
    }

    /**
     * 房型列表-详细
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/lists")
    @ResponseBody
    public Rt queryHotelTypeFullList(Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomTypeQuery query = new HotelRoomTypeQuery();
        return hotelRoomTypeService.queryHotelRoomTypeFullByPage(query,handle.getPage(),handle.getLimit());
    }

    /**
     * 根据主键查询房型
     * @param roomTypeId
     * @return
     */
    @RequestMapping("/find-one")
    @ResponseBody
    public R findOneById(Long roomTypeId){
        if(roomTypeId==null){
            return R.error(403,"主键不能为空");
        }
        HotelRoomTypeFullVO hotelRoomTypeFullVO = hotelRoomTypeService.findOneById(roomTypeId);
        return (hotelRoomTypeFullVO!=null)?R.ok("success",hotelRoomTypeFullVO):R.error();
    }

    /**
     * 增加房型信息
     * @param hotelRoomType hotelRoomType
     * @return r
     */
    @PostMapping("/add")
    @ResponseBody
    public R addHotelType(@RequestBody HotelRoomTypeDTO hotelRoomType){
        if(hotelRoomType.getIsHotelId() == null){
            return R.error(403,"[isHotelId]不能为空");
        }
        Boolean flag = hotelRoomTypeService.addRecord(hotelRoomType, hotelRoomType.getDictId());
        return (flag)?R.ok():R.error();
    }

}
