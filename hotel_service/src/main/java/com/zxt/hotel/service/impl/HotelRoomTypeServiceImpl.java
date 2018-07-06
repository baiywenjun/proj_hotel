package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelRoomType;
import com.zxt.hotel.entity.HotelTypeDictRel;
import com.zxt.hotel.mapper.HotelRoomTypeMapper;
import com.zxt.hotel.mapper.HotelTypeDictRelMapper;
import com.zxt.hotel.pojo.HotelRoomTypeFullVO;
import com.zxt.hotel.pojo.HotelRoomTypeNHotelVO;
import com.zxt.hotel.pojo.HotelRoomTypeQuery;
import com.zxt.hotel.service.HotelRoomTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 酒店sku 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
@Service
public class HotelRoomTypeServiceImpl extends ServiceImpl<HotelRoomTypeMapper, HotelRoomType> implements HotelRoomTypeService {

    @Autowired
    private HotelRoomTypeMapper hotelRoomTypeMapper;

    @Autowired
    private HotelTypeDictRelMapper hotelTypeDictRelMapper;

    @Override
    /**
     * 给profile使用
     * @param query
     * @return
     */
    public List<HotelRoomType> queryHotelRoomType(HotelRoomTypeQuery query){
        Wrapper<HotelRoomType> wrapper = new EntityWrapper<>();
        if(query.getIsHotelId() != null){
            wrapper.eq("is_hotel_id",query.getIsHotelId());
        }
        return this.selectList(wrapper);
    }

    @Override
    public Rt queryHotelRoomTypeByPage(HotelRoomTypeQuery query, Integer page, Integer limit){
        Wrapper<HotelRoomType> wrapper = new EntityWrapper<>();
        if(query.getIsHotelId() != null){
            wrapper.eq("is_hotel_id",query.getIsHotelId());
        }
        int count = this.selectCount(wrapper);
        Page<HotelRoomType> hotelInfoPage = this.selectPage(new Page<>(page, limit), wrapper);
        List<HotelRoomType> records = hotelInfoPage.getRecords();
        return Rt.ok(count,records);
    }

    @Override
    public Rt queryHotelRoomTypeFullByPage(HotelRoomTypeQuery query, Integer page, Integer limit){
        Wrapper<HotelRoomType> wrapper = new EntityWrapper<>();
        if(query.getIsHotelId() != null){
            wrapper.eq("is_hotel_id",query.getIsHotelId());
        }
        int count = this.selectCount(wrapper);
        List<HotelRoomTypeFullVO> hotelRoomTypeFullVOList = hotelRoomTypeMapper.queryHotelRoomTypeFullByPage(new Page(page, limit), query);
        return Rt.ok(count,hotelRoomTypeFullVOList);
    }

    @Override
    public HotelRoomTypeFullVO findOneById(Long roomTypeId){
         return hotelRoomTypeMapper.queryById(roomTypeId);
    }


    @Override
    /**
     * 新增房型信息
     * @param hotelRoomType
     * @param dictId
     * @return
     */
    public Boolean addRecord(HotelRoomType hotelRoomType, Long dictId){
        hotelRoomType.setTypeStatus("1");
        boolean insert1 = this.insert(hotelRoomType);
        Long roomTypeId = hotelRoomType.getRoomTypeId();
        HotelTypeDictRel hotelTypeDictRel = new HotelTypeDictRel();
        hotelTypeDictRel.setRoomTypeId(roomTypeId);
        hotelTypeDictRel.setDictId(dictId);
        Integer insert2 = hotelTypeDictRelMapper.insert(hotelTypeDictRel);
        return (insert1 && insert2 > 0);
    }

    /**
     * 2018-06-20添加
     * 跟 queryHotelRoomTypeFullByPage 相似
     * @param query
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Rt queryHotelRoomTypeNHotelByPage(HotelRoomTypeQuery query, Integer page, Integer limit) {
        Wrapper<HotelRoomType> wrapper = new EntityWrapper<>();
        if(query.getIsHotelId() != null){
            wrapper.eq("is_hotel_id",query.getIsHotelId());
        }
        int count = this.selectCount(wrapper);
        List<HotelRoomTypeNHotelVO> hotelRoomTypeFullVOList = hotelRoomTypeMapper.queryHotelRoomTypeNHotelByPage(new Page(page, limit), query);
        return Rt.ok(count,hotelRoomTypeFullVOList);
    }

    @Override
    public R addHotelRoomType(Map map) {
        HotelRoomType hotelRoomType = new HotelRoomType();
        try {
            hotelRoomType.setIsHotelId(Long.parseLong(map.get("hotelName") + ""));
            hotelRoomType.setTypeCode(map.get("typeCode") + "");
            hotelRoomType.setTypeName(map.get("typeName") + "");
            hotelRoomType.setTypeImg(map.get("typeImg") + "");
            hotelRoomType.setTypePrice(Integer.parseInt(map.get("typePrice") + ""));
            hotelRoomType.setTypeContent(map.get("typeContent") + "");
            hotelRoomType.setTypeStatus(map.get("typeStatus") + "");
            hotelRoomType.setCreateTime(new Date());
        } catch (Exception e) {
            return R.error(0, e.getMessage());
        }

        //===================== 处理 typeSpec start
        StringBuilder typeSpecBuilder = new StringBuilder();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            Object value=map.get(next);
            if (next.contains("typeSpec[") && value.toString().contains("on")) {
                next=next.trim().replace("typeSpec[","").replace("]","");
                typeSpecBuilder.append(next+ ",");
            }
        }
        String[] specs=typeSpecBuilder.toString().split(",");
        Integer[] specsInt=new Integer[specs.length];
        for(int i=0;i<specs.length;i++){
            specsInt[i]=Integer.parseInt(specs[i]);
        }
        Arrays.sort(specsInt);
        String join = StringUtils.join(specsInt, ",");
        hotelRoomType.setTypeSpec(join);
        //===================== 处理 typeSpec end

        Boolean flag= this.insert(hotelRoomType);
        return (flag) ? R.ok() : R.error();
    }
}
