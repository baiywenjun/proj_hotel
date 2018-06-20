package com.zxt.hotel.service.impl;

import com.zxt.hotel.entity.HotelContent;
import com.zxt.hotel.mapper.HotelContentMapper;
import com.zxt.hotel.service.HotelContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 酒店描述相关 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@Service
public class HotelContentServiceImpl extends ServiceImpl<HotelContentMapper, HotelContent> implements HotelContentService {

    @Override
    /**
     * 批量新增酒店描述记录
     * @param hotelContentList hotelContentList
     * @return flag
     */
    public List<Long> batchInsertRecord(List<HotelContent> hotelContentList){
        List<Long> contentIdList = new ArrayList<>();
        for (HotelContent hotelContent : hotelContentList) {
            hotelContent.setType("H01");
            hotelContent.setCreateTime(new Date());
            this.insert(hotelContent);
            Long contentId = hotelContent.getContentId();
            contentIdList.add(contentId);
        }
        return contentIdList;
    }
}
