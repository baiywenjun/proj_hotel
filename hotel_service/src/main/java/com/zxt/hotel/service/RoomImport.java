package com.zxt.hotel.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zxt.common.excel.HxlsOptRowsCustomInterface;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.entity.HotelRoom;
import com.zxt.hotel.entity.HotelRoomType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Title: 房间导入
 * Description: master
 * author: wenjun
 * date: 2018/6/23 15:53
 */
public class RoomImport implements HxlsOptRowsCustomInterface {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public String saveOptRows(int sheetIndex, int curRow, List<String> rowlist, Map<String, Object> param) throws Exception {
        log.info("获取参数:"+param);
        WebApplicationContext application = ContextLoader.getCurrentWebApplicationContext();
        String[] beanDefinitionNames = application.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            log.info(beanDefinitionName);
        }
        HotelRoomService hotelRoomService = (HotelRoomService) application.getBean("hotelRoomServiceImpl");
        HotelInfoService hotelInfoService = (HotelInfoService) application.getBean("hotelInfoServiceImpl");
        HotelRoomTypeService hotelTypeService = (HotelRoomTypeService) application.getBean("hotelRoomTypeServiceImpl");
        // 获取cell的中的值
        String hotelName = rowlist.get(0);
        Long hotelId = this.getHotelId(hotelInfoService, hotelName);
        String typeCode = rowlist.get(1);
        Long roomTypeId = this.getRoomTypeId(hotelTypeService, typeCode);
        String roomNo = rowlist.get(2);
        String storey = rowlist.get(3);
        String qrCode = rowlist.get(4);
        String qrCodeContent = rowlist.get(5);
        String devNo = rowlist.get(6);
        // 校验
        if(StringUtils.isEmpty(hotelName)){
            return "酒店名称不能为空";
        }
        if(hotelId == null){
            return "酒店名称错误";
        }
        if(StringUtils.isEmpty(typeCode)){
            return "房型编码不能为空";
        }
        if(roomTypeId == null){
            return "房型编码错误";
        }
        if(StringUtils.isEmpty(roomNo)){
            return "房型编码不能为空";
        }
        // 添加记录
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setIsHotelId(hotelId);
        hotelRoom.setHotelName(hotelName);
        hotelRoom.setIsRoomTypeId(roomTypeId);
        hotelRoom.setRoomNo(roomNo);
        hotelRoom.setStorey(storey);
        hotelRoom.setQrCode(qrCode);
        hotelRoom.setQrCodeContent(qrCodeContent);
        hotelRoom.setDevNo(devNo);
        hotelRoom.setCreateTime(new Date());
        Long roomId = hotelRoomService.addRecord(hotelRoom);
        log.info("添加成功:"+curRow+",房间主键:"+roomId);
        return HxlsOptRowsCustomInterface.SUCCESS;
    }

    private Long getHotelId(HotelInfoService hotelInfoService,String hotelName){
        Wrapper<HotelInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("name",hotelName);
        HotelInfo hotelInfo = hotelInfoService.selectOne(wrapper);
        if(hotelInfo == null){
            log.error("酒店名称[" + hotelName + "]在系统中未查询到");
            return null;
        }
        return hotelInfo.getHotelId();

    }

    private Long getRoomTypeId(HotelRoomTypeService hotelRoomTypeService, String typeCode){
        Wrapper<HotelRoomType> wrapper = new EntityWrapper<>();
        wrapper.eq("type_code", typeCode);
        HotelRoomType hotelRoomType = hotelRoomTypeService.selectOne(wrapper);
        if(hotelRoomType == null){
            log.error("房型编号[" + typeCode + "]在系统中未查询到");
            return null;
        }
        return hotelRoomType.getRoomTypeId();
    }

}
