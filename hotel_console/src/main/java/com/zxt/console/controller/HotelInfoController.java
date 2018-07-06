package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.console.pojo.HotelInfoDTO;
import com.zxt.hotel.entity.HotelContent;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.pojo.HotelInfoFullVO;
import com.zxt.hotel.pojo.HotelInfoQuery;
import com.zxt.hotel.service.HotelContentService;
import com.zxt.hotel.service.HotelInfoContentRelService;
import com.zxt.hotel.service.HotelInfoDictRelService;
import com.zxt.hotel.service.HotelInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Title: 酒店管理
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/18 16:34
 */
@Controller
@RequestMapping("/hotel/info")
public class HotelInfoController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HotelInfoService hotelInfoService;

    @Autowired
    private HotelInfoDictRelService hotelInfoDictRelService;

    @Autowired
    private HotelContentService hotelContentService;

    @Autowired
    private HotelInfoContentRelService hotelInfoContentRelService;

    @RequestMapping("/page")
    public String hotelPage(){
        return "components/hotel";
    }

    @RequestMapping("/add-page")
    public String hotelAddPage(){
        return "components/hotel-add";
    }

    @RequestMapping("/update-page")
    public String hotelUpdatePage(Model model,String id){
        HotelInfoFullVO hotelInfoFullVO = hotelInfoService.queryHotelInfoFUllVOById(Long.parseLong(id));
        model.addAttribute("hotelInfo",hotelInfoFullVO);
        model.addAttribute("operate","update");
        return "components/hotel-add";
    }

    @RequestMapping("/view-page")
    public String hotelViewPage(Model model, String id){
        HotelInfoFullVO hotelInfoFullVO = hotelInfoService.queryHotelInfoFUllVOById(Long.parseLong(id));
        model.addAttribute("hotelInfo",hotelInfoFullVO);
        model.addAttribute("operate","view");
        return "components/hotel-add";
    }

    @RequestMapping("/amap")
    public String aMap(String id){
        return "components/hotel-amap";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Rt queryHotelIntoList(String hotelName, Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelInfoQuery query = new HotelInfoQuery();
        query.setName(hotelName);
        //return hotelInfoService.queryHotelInfoFullByPage(query, handle.getPage(), handle.getLimit());
        Map<String,Object> map = new HashMap<>();
        return hotelInfoService.queryHotelInfoFullByAuthAndPage(map,query,handle.getPage(),handle.getLimit());
    }


    /**
     * 添加酒店
     * @param hotelInfoFull hotelInfo,dictIds,hotelContentList
     * @return r
     */
    @PostMapping("/add")
    @ResponseBody
    public R addHotelInfo(@RequestBody HotelInfoDTO hotelInfoFull){
        // todo 参数校验后续
        HotelInfo hotelInfo = hotelInfoFull.getHotelInfo();
        List<Long> businessIdArr = hotelInfoFull.getBusinessId();
        List<HotelContent> hotelContentList = hotelInfoFull.getHotelContentList();
        log.info(businessIdArr.toString());
        log.info(hotelContentList.toString());
        Long hotelId = hotelInfoService.addHotelInfo(hotelInfo);
        if(hotelId == null){
            return R.error("酒店信息新增失败");
        }
        Boolean flag_a = false;
        Boolean flag_b = false;
        if(businessIdArr.size() > 0){
            flag_a = hotelInfoDictRelService.batchInsertRecord(hotelId, businessIdArr);
        }
        if(hotelContentList.size() > 0){
            List<Long> contentIdList = hotelContentService.batchInsertRecord(hotelContentList);
            flag_b = hotelInfoContentRelService.batchInsertRecord(hotelId, contentIdList);
        }
        return (flag_a && flag_b)?R.ok():R.error();
    }

    @PostMapping("/update")
    @ResponseBody
    public R updateHotelInfo(@RequestBody HotelInfoDTO hotelInfoFull){
        // warning 参数前端校验
        // 实体主键必校验
        HotelInfo hotelInfo = hotelInfoFull.getHotelInfo();
        List<Long> businessIdList = hotelInfoFull.getBusinessId();
        List<HotelContent> hotelContentList = hotelInfoFull.getHotelContentList();
        Long hotelId = hotelInfo.getHotelId();
        if(hotelInfo == null || hotelId == null){
            return R.error(403,"主键不能为空");
        }
        if(StringUtils.isEmpty(hotelInfo.getImgUrl())){
            hotelInfo.setImgUrl(null);
        }
        if(StringUtils.isEmpty(hotelInfo.getWelcomeImgUrl())){
            hotelInfo.setWelcomeImgUrl(null);
        }
        Boolean flag_a = hotelInfoService.updateHotelInfo(hotelInfo);
        Boolean flag_b = true;
        if(businessIdList.size() > 0){
            flag_b = hotelInfoDictRelService.rebuildRecord(hotelId, businessIdList);
        }
        Boolean flag_c = hotelInfoContentRelService.deleteRecordWithContent(hotelId);
        Boolean flag_d = true;
        if(hotelContentList.size() > 0){
            List<Long> contentIdList = hotelContentService.batchInsertRecord(hotelContentList);
            flag_d = hotelInfoContentRelService.batchInsertRecord(hotelId, contentIdList);
        }
        return (flag_a && flag_b && flag_c && flag_d)?R.ok():R.error();
    }
}
