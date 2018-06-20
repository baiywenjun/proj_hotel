package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.common.upload.UpResult;
import com.zxt.common.upload.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/6/13 17:53
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UploadService uploadService;

    @Value("${upload_path}")
    private String uploadPath;

    @Value("${hotel_logo}")
    private String hotelLogo;

    @Value("${hotel_welcome}")
    private String hotelWelcome;

    @Value("${hotel_content}")
    private String hotelContent;

    @RequestMapping("/hotel-logo")
    public R uploadHotelLogo(HttpServletRequest request, HttpServletResponse response){
        String uploadPath = this.uploadPath + this.hotelLogo;
        return extractUpload(request, response, uploadPath,this.hotelLogo);
    }

    @RequestMapping("/hotel-welcome")
    public R uploadHotelWelcome(HttpServletRequest request, HttpServletResponse response){
        String uploadPath = this.uploadPath + this.hotelWelcome;
        return extractUpload(request, response, uploadPath,this.hotelWelcome);
    }

    @RequestMapping("/hotel-content")
    public R uploadHotelContent(HttpServletRequest request, HttpServletResponse response){
        String uploadPath = this.uploadPath + this.hotelContent;
        return extractUpload(request, response, uploadPath,this.hotelContent);
    }

    private R extractUpload(HttpServletRequest request, HttpServletResponse response, String uploadPath, String relativePath) {
        UpResult upResult = null;
        try {
            upResult = uploadService.doPost(uploadPath, request, response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(upResult != null){
            // 返回相对路径至前端
            String uuidName = upResult.getUuidName();
            String relativeFile = relativePath + uuidName;
            Map<String,Object> map = new HashMap<>(1);
            map.put("relativeFile",relativeFile);
            return R.ok(map);
        }
        return R.error();
    }
}
