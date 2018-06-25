package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.upload.UpResult;
import com.zxt.common.upload.UploadService;
import com.zxt.common.util.PageUtil;
import com.zxt.console.pojo.HotelRoomTypeDTO;
import com.zxt.hotel.pojo.HotelRoomTypeFullVO;
import com.zxt.hotel.pojo.HotelRoomTypeQuery;
import com.zxt.hotel.service.HotelRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @RequestMapping("page2")
    public String hotelRoomTypePage2(){
        return "components/hotel-type2";
    }

    private final String uploadPath="/home/bluedragon/workspaces/upload";

    /**
     * 房型列表-简要
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Rt queryHotelTypeList(Long hotelId, Integer page, Integer limit) {
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomTypeQuery query = new HotelRoomTypeQuery();
        query.setIsHotelId(hotelId);
        return hotelRoomTypeService.queryHotelRoomTypeByPage(query, handle.getPage(), handle.getLimit());
    }

    /**
     * 房型列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/lists")
    @ResponseBody
    public Rt queryHotelTypeList(HttpServletRequest request,
                                     Long roomTypeId,
                                     Integer page, Integer limit) {
        // todo 后续基于权限，查询登陆人所属权限的订单
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomTypeQuery query = new HotelRoomTypeQuery();
        query.setRoomTypeId(roomTypeId);
        return hotelRoomTypeService.queryHotelRoomTypeFullByPage(query, handle.getPage(), handle.getLimit());
    }

    /**
     * 房型列表-详细
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/details")
    @ResponseBody
    public Rt queryHotelTypeFullList(HttpServletRequest request,
                                     Long roomTypeId,
                                     Integer page, Integer limit) {
        // todo 后续基于权限，查询登陆人所属权限的订单
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomTypeQuery query = new HotelRoomTypeQuery();
        query.setRoomTypeId(roomTypeId);
        return hotelRoomTypeService.queryHotelRoomTypeFullByPage(query, handle.getPage(), handle.getLimit());
    }

    /**
     * 根据主键查询房型
     *
     * @param roomTypeId
     * @return
     */
    @RequestMapping("/find-one")
    @ResponseBody
    public R findOneById(Long roomTypeId) {
        if (roomTypeId == null) {
            return R.error(403, "主键不能为空");
        }
        HotelRoomTypeFullVO hotelRoomTypeFullVO = hotelRoomTypeService.findOneById(roomTypeId);
        return (hotelRoomTypeFullVO != null) ? R.ok("success", hotelRoomTypeFullVO) : R.error();
    }

    /**
     * 测试增加房型信息
     *
     * @return r
     */
    @GetMapping("/addTest")
    @ResponseBody
    public R addTest() {
        return R.ok();
    }

    /**
     * 增加房型信息
     *
     * @param hotelRoomType hotelRoomType
     * @return r
     */
    @PostMapping("/add")
    @ResponseBody
    public R addHotelType(@RequestBody HotelRoomTypeDTO hotelRoomType) {
        if (hotelRoomType.getIsHotelId() == null) {
            return R.error(403, "[isHotelId]不能为空");
        }
        Boolean flag = hotelRoomTypeService.addRecord(hotelRoomType, hotelRoomType.getDictId());
        return (flag) ? R.ok() : R.error();
    }


    /**
     * 增加上传文件
     *
     * @return r
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    R headImg(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();

        String prefix = "";
        String dateStr = "";
        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                dateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
                String filepath = request.getServletPath() + "/static/" + dateStr + "." + prefix;
                filepath = filepath.replace("\\", "/");
                File files = new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }else{
                return R.error(0,"文件为空");
            }
        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
                return R.error();
            }
        }
        Map<String,Object> data=new HashMap<>();
        data.put("src",uploadPath+"/static/" + dateStr + "." + prefix);
        return R.ok();
    }
}
