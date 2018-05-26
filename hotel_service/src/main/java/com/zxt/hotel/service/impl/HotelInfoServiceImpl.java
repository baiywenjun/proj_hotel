package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelContent;
import com.zxt.hotel.entity.HotelDict;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.mapper.HotelContentMapper;
import com.zxt.hotel.mapper.HotelDictMapper;
import com.zxt.hotel.mapper.HotelInfoMapper;
import com.zxt.hotel.pojo.HotelInfoExt;
import com.zxt.hotel.pojo.HotelInfoFullVO;
import com.zxt.hotel.pojo.HotelInfoFullVOBack;
import com.zxt.hotel.pojo.HotelInfoQuery;
import com.zxt.hotel.service.HotelInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 酒店信息 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
@Service
public class HotelInfoServiceImpl extends ServiceImpl<HotelInfoMapper, HotelInfo> implements HotelInfoService {

    @Autowired
    private HotelDictMapper hotelDictMapper;

    @Autowired
    private HotelContentMapper hotelContentMapper;

    @Autowired
    private HotelInfoMapper hotelInfoMapper;

    @Override
    public Rt queryHotelInfoByPage(HotelInfoQuery query, Integer page, Integer limit){
        Wrapper<HotelInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotEmpty(query.getName())){
            wrapper.like("name",query.getName());
        }
        int count = this.selectCount(wrapper);
        Page<HotelInfo> hotelInfoPage = this.selectPage(new Page<>(page, limit), wrapper);
        List<HotelInfo> records = hotelInfoPage.getRecords();
        return Rt.ok(count,records);
    }

    @Override
    /**
     * 查询酒店信息
     * @param page page
     * @param limit limit
     * @return rt
     */
    public Rt queryHotelInfoFullByPage(HotelInfoQuery query, Integer page, Integer limit){
        Wrapper<HotelInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotEmpty(query.getName())){
            wrapper.like("name",query.getName());
        }
        int count = this.selectCount(wrapper);
        //Page<HotelInfo> hotelInfoPage = this.selectPage(new Page<>(page, limit), wrapper);
        //List<HotelInfo> records = hotelInfoPage.getRecords();
        List<HotelInfoExt> hotelInfoExts = hotelInfoMapper.queryHotelInfoExtByPage(new Page<>(page, limit), query);
        List<HotelInfoFullVO> hotelInfoFullList = new ArrayList<>();
        for (HotelInfoExt hotelInfo : hotelInfoExts) {
            // 创建数组
            List<HotelDict> hotelDictList = new ArrayList<>();
            List<HotelContent> hotelContentList = new ArrayList<>();
            // 规格信息
            String businessIds = hotelInfo.getBusiness();
            if(StringUtils.isNotEmpty(businessIds)){
                String[] businessArr = businessIds.split(",");
                for (String dictId : businessArr) {
                    HotelDict hotelDict = hotelDictMapper.selectById(dictId);
                    hotelDictList.add(hotelDict);
                }
            }
            // 酒店描述，包含图片
           String descriptionIds = hotelInfo.getDescription();
            if(StringUtils.isNotEmpty(descriptionIds)){
                String[] contentIdArr = descriptionIds.split(",");
                for (String contentId : contentIdArr) {
                    HotelContent hotelContent = hotelContentMapper.selectById(contentId);
                    hotelContentList.add(hotelContent);
                }
            }
            // 组装添加
            HotelInfoFullVO hotelInfoFull = new HotelInfoFullVO();
            hotelInfoFull.setHotelInfoExt(hotelInfo);
            hotelInfoFull.setHotelDictList(hotelDictList);
            hotelInfoFull.setHotelContentList(hotelContentList);
            hotelInfoFullList.add(hotelInfoFull);
        }
        Collections.sort(hotelInfoFullList, new Comparator<HotelInfoFullVO>() {
            @Override
            public int compare(HotelInfoFullVO o1, HotelInfoFullVO o2) {
                String o1Distance = o1.getHotelInfoExt().getDistance();
                String o2Distance = o2.getHotelInfoExt().getDistance();
                int o1d = Integer.parseInt(o1Distance);
                int o2d = Integer.parseInt(o2Distance);
                return (o1d-o2d);
            }
        });
        return Rt.ok(count,hotelInfoFullList);
    }

    @Override
    public Boolean addHotelInfo(HotelInfo hotel) {
        hotel.setCreateTime(new Date());
        boolean insert = this.insert(hotel);
        return insert;

    }

    @Override
    public Boolean updateHotelInfo(HotelInfo hotel) {
        return this.updateById(hotel);
    }


}
