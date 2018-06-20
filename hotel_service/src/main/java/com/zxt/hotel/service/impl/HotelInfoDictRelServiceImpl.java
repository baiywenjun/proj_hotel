package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.hotel.entity.HotelInfoDictRel;
import com.zxt.hotel.mapper.HotelInfoDictRelMapper;
import com.zxt.hotel.service.HotelInfoDictRelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 酒店与字典关系表 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-06-12
 */
@Service
public class HotelInfoDictRelServiceImpl extends ServiceImpl<HotelInfoDictRelMapper, HotelInfoDictRel> implements HotelInfoDictRelService {

    @Override
    /**
     * 批量新酒店与设施的关联记录
     * @param hotelId hotelId
     * @param dictIdList dictIdList
     * @return flag
     */
    public Boolean batchInsertRecord(Long hotelId, List<Long> dictIdList){
        List<HotelInfoDictRel> relList = new ArrayList<>();
        for (Long dictId : dictIdList) {
            HotelInfoDictRel rel = new HotelInfoDictRel();
            rel.setHotelId(hotelId);
            rel.setDictId(dictId);
            relList.add(rel);
        }
        return this.insertBatch(relList);
    }


    @Override
    /**
     * 重新对应酒店与设施的关联记录
     * @param hotelId
     * @param dictIdList
     * @return
     */
    public Boolean rebuildRecord(Long hotelId,List<Long> dictIdList){
        Wrapper<HotelInfoDictRel> wrapper = new EntityWrapper<>();
        wrapper.eq("hotel_id",hotelId);
        boolean delete_flag = this.delete(wrapper);
        Boolean flag = this.batchInsertRecord(hotelId, dictIdList);
        return (delete_flag && flag);
    }

}
