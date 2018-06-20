package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zxt.hotel.entity.HotelInfoContentRel;
import com.zxt.hotel.entity.HotelInfoDictRel;
import com.zxt.hotel.mapper.HotelInfoContentRelMapper;
import com.zxt.hotel.service.HotelContentService;
import com.zxt.hotel.service.HotelInfoContentRelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 酒店与描述关系 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-06-12
 */
@Service
public class HotelInfoContentRelServiceImpl extends ServiceImpl<HotelInfoContentRelMapper, HotelInfoContentRel> implements HotelInfoContentRelService {

    @Autowired
    private HotelContentService hotelContentService;

    @Override
    public Boolean batchInsertRecord(Long hotelId, List<Long> contentIdList){
        List<HotelInfoContentRel> relList = new ArrayList<>();
        for (Long contentId : contentIdList) {
            HotelInfoContentRel rel = new HotelInfoContentRel();
            rel.setHotelId(hotelId);
            rel.setContentId(contentId);
            relList.add(rel);
        }
        return  this.insertBatch(relList);
    }

    @Override
    public Boolean deleteRecordWithContent(Long hotelId){
        Wrapper<HotelInfoContentRel> wrapper = new EntityWrapper<>();
        wrapper.eq("hotel_id",hotelId);
        List<HotelInfoContentRel> hotelInfoContentRelList = this.selectList(wrapper);
        boolean flag_b = true;
        if(hotelInfoContentRelList.size()>0){
            List<Long> contentIdList = new ArrayList<>();
            for (HotelInfoContentRel hotelInfoContentRel : hotelInfoContentRelList) {
                Long contentId = hotelInfoContentRel.getContentId();
                contentIdList.add(contentId);
            }
            flag_b = hotelContentService.deleteBatchIds(contentIdList);
        }
        boolean flag_a = this.delete(wrapper);
        return (flag_a && flag_b);
    }
}
