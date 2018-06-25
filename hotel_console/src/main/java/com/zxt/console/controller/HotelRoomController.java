package com.zxt.console.controller;

import com.zxt.common.excel.HxlsCustomRead;
import com.zxt.common.exception.RRException;
import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.upload.UpResult;
import com.zxt.common.upload.UploadService;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.entity.HotelRoom;
import com.zxt.hotel.pojo.HotelRoomFullVO;
import com.zxt.hotel.pojo.HotelRoomQuery;
import com.zxt.hotel.service.HotelRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: 房间
 * Description: todoedit
 * author: wenjun
 * date: 2018/6/6 16:48
 */
@Controller
@RequestMapping("/hotel/room")
public class HotelRoomController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HotelRoomService hotelRoomService;

    @Autowired
    private UploadService uploadService;

    @Value("${upload_path}")
    private String  basePath;

    @Value("${hotel_room_excel}")
    private String  dirPath;


    @RequestMapping("/page")
    public String hotelRoomPage(){
        return "components/hotel-room";
    }

    @RequestMapping("/add-page")
    public String hotelRoomAddPage(){
        return "components/room-add";
    }

    @RequestMapping("/import-page")
    public String hotelRoomImportPage(){
        return "components/room-import";
    }

    /**
     * 房间查询
     * @param hotelId
     * @param roomTypeId
     * @param stayStatus
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Rt queryHotelRoomList(Long hotelId, Long roomTypeId, String stayStatus, Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomQuery query = new HotelRoomQuery();
        query.setIsHotelId(hotelId);
        query.setIsRoomTypeId(roomTypeId);
        query.setStayStatus(stayStatus);
        return hotelRoomService.queryHotelRoomByPage(query, handle.getPage(), handle.getLimit());
    }

    /**
     * 房间列表详细
     * @param hotelId
     * @param roomTypeId
     * @param stayStatus
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/lists")
    @ResponseBody
    public Rt queryHotelRoomFullList(Long hotelId, Long roomTypeId, String stayStatus, Integer page, Integer limit) {
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomQuery query = new HotelRoomQuery();
        query.setIsHotelId(hotelId);
        query.setIsRoomTypeId(roomTypeId);
        query.setStayStatus(stayStatus);
        return hotelRoomService.queryHotelRoomExtByPage(query,handle.getPage(),handle.getLimit());
    }

    /**
     * 根据主键获取房间详细信息
     * @param roomId
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public R getRoomInfoByScanQr(Long roomId){
        if(roomId == null){
            return R.error(403,"房间主键不能为空");
        }
        HotelRoomFullVO hotelRoomFullVO = hotelRoomService.getById(roomId);
        return R.ok("success",hotelRoomFullVO);
    }

    /**
     * 添加房间记录
     * @param hotelRoom
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public R addRoom(@RequestBody HotelRoom hotelRoom){
        if(hotelRoom.getIsHotelId() == null){
            return R.error(403,"[isHotelId]不能为空");
        }
        if(hotelRoom.getIsRoomTypeId() == null){
            return R.error(403,"[isRoomTypeId]不能为空");
        }
        Long hotelRoomId = hotelRoomService.addRecord(hotelRoom);
        return (hotelRoomId!=null)?R.ok("success",hotelRoomId):R.error();
    }

    /**
     * excel上传
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public R uploadExcel(HttpServletRequest request, HttpServletResponse response){
        String uploadDirPath = basePath + dirPath;
        log.info("上传的路径是:"+uploadDirPath);
        try {
            UpResult upResult = uploadService.doPost(uploadDirPath, request, response);
            String filePath = upResult.getFilePath();
            Map<String,Object> map = new HashMap<>(1);
            map.put("filePath",filePath);
            return R.ok(map);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return R.error("上传失败");
    }


    /**
     * 数据导入
     * @param filePath
     * @return
     */
    @RequestMapping("/import")
    @ResponseBody
    public R importData(String filePath,HttpServletRequest request){
        log.info(filePath);
        String result = hotelRoomService.processImportData(filePath);
        //File file = new File(filePath);
        //boolean flag = file.delete();
        return R.ok(result);
    }



}
