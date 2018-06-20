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
import com.zxt.hotel.pojo.HotelInfoFullDistanceVO;
import com.zxt.hotel.pojo.HotelInfoFullVO;
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
     * 酒店列表不带距离
     * @param query
     * @param page
     * @param limit
     * @return
     */
    public Rt queryHotelInfoFullByPage(HotelInfoQuery query, Integer page, Integer limit){
        Wrapper<HotelInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotEmpty(query.getName())){
            wrapper.like("name",query.getName());
        }
        int count = this.selectCount(wrapper);
        List<HotelInfoFullVO> hotelInfoFullVOList = hotelInfoMapper.queryHotelInfoFullByPage(wrapper,page-1,limit);
        return Rt.ok(count,hotelInfoFullVOList);
    }

    @Override
    /**
     * 根据主键查找酒店详细信息
     * @param hotelId
     * @return
     */
    public HotelInfoFullVO queryHotelInfoFUllVOById(Long hotelId){
        Wrapper<HotelInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("hotel_id",hotelId);
        List<HotelInfoFullVO> hotelInfoFullVOS = hotelInfoMapper.queryHotelInfoFullByPage(wrapper,0,1);
        if(hotelInfoFullVOS!=null && hotelInfoFullVOS.size()>0){
            return hotelInfoFullVOS.get(0);
        }
        return null;
    }

    @Override
    /**
     * 酒店列表带距离
     * @param query
     * @param page
     * @param limit
     * @return
     */
    public Rt queryHotelInfoFullDistanceByPage(HotelInfoQuery query, Integer page, Integer limit){
        Wrapper<HotelInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotEmpty(query.getName())){
            wrapper.like("name",query.getName());
        }
        int count = this.selectCount(wrapper);
        List<HotelInfoFullVO> hotelInfoFullVOList = hotelInfoMapper.queryHotelInfoFullDistanceByPage(query, wrapper,page-1,limit);
        return Rt.ok(count,hotelInfoFullVOList);
    }

    @Override
    /**
     * 酒店列表带距离,过渡
     * @param query
     * @param page
     * @param limit
     * @return
     */
    public Rt queryHotelInfoFullDistanceByPageOver(HotelInfoQuery query, Integer page, Integer limit){
        Wrapper<HotelInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotEmpty(query.getName())){
            wrapper.like("name",query.getName());
        }
        int count = this.selectCount(wrapper);
        // todo 此处为过渡方法，小程序按最新接口后，此处可废弃
        List<HotelInfoFullVO> hotelInfoFullVOList = hotelInfoMapper.queryHotelInfoFullDistanceByPage(query, wrapper, page - 1, limit);
        List<HotelInfoExt> hotelInfoExtList = hotelInfoMapper.queryHotelInfoExtByPage(new Page<>(page, limit), query);
        List<HotelInfoFullDistanceVO> hotelInfoListOver = new ArrayList<>();
        for (HotelInfoExt hotelInfoExt : hotelInfoExtList) {
            for (HotelInfoFullVO hotelInfoFullVO : hotelInfoFullVOList) {
                if(hotelInfoExt.getHotelId() == hotelInfoFullVO.getHotelId()){
                    HotelInfoFullDistanceVO temp = new HotelInfoFullDistanceVO();
                    temp.setHotelInfoExt(hotelInfoExt);
                    temp.setHotelDictList(hotelInfoFullVO.getHotelDictList());
                    temp.setHotelContentList(hotelInfoFullVO.getHotelContentList());
                    hotelInfoListOver.add(temp);
                }

            }

        }
        //List<HotelInfoFullDistanceVO> hotelInfoFullDistanceVOS = hotelInfoMapper.queryHotelInfoFullDistanceByPageOver(query, wrapper,page-1,limit);
        return Rt.ok(count,hotelInfoListOver);
    }

    @Deprecated
    @Override
    /**
     * 查询酒店信息
     * @param page page
     * @param limit limit
     * @return rt
     */
    public Rt queryHotelInfoFullByPageDiscard(HotelInfoQuery query, Integer page, Integer limit){
        Wrapper<HotelInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotEmpty(query.getName())){
            wrapper.like("name",query.getName());
        }
        int count = this.selectCount(wrapper);
        //Page<HotelInfo> hotelInfoPage = this.selectPage(new Page<>(page, limit), wrapper);
        //List<HotelInfo> records = hotelInfoPage.getRecords();
        List<HotelInfoExt> hotelInfoExts = hotelInfoMapper.queryHotelInfoExtByPage(new Page<>(page, limit), query);
        List<HotelInfoFullDistanceVO> hotelInfoFullList = new ArrayList<>();
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
           String contentIds = hotelInfo.getContent();
            if(StringUtils.isNotEmpty(contentIds)){
                String[] contentIdArr = contentIds.split(",");
                for (String contentId : contentIdArr) {
                    HotelContent hotelContent = hotelContentMapper.selectById(contentId);
                    hotelContentList.add(hotelContent);
                }
            }
            // 组装添加
            HotelInfoFullDistanceVO hotelInfoFull = new HotelInfoFullDistanceVO();
            hotelInfoFull.setHotelInfoExt(hotelInfo);
            hotelInfoFull.setHotelDictList(hotelDictList);
            hotelInfoFull.setHotelContentList(hotelContentList);
            hotelInfoFullList.add(hotelInfoFull);
        }
        Collections.sort(hotelInfoFullList, new Comparator<HotelInfoFullDistanceVO>() {
            @Override
            public int compare(HotelInfoFullDistanceVO o1, HotelInfoFullDistanceVO o2) {
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
    public Long addHotelInfo(HotelInfo hotel) {
        hotel.setCreateTime(new Date());
        boolean insert = this.insert(hotel);
        Long hotelId = hotel.getHotelId();
        return hotelId;
    }

    @Override
    public Boolean updateHotelInfo(HotelInfo hotel) {
        return this.updateById(hotel);
    }


}
