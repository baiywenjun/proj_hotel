package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.ServeType;
import com.zxt.hotel.mapper.ServeTypeMapper;
import com.zxt.hotel.service.ServeTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务类型 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@Service
public class ServeTypeServiceImpl extends ServiceImpl<ServeTypeMapper, ServeType> implements ServeTypeService {

    @Override
    /**
     * 酒店服务列表
     * @return rt
     */
    public Rt serveTypeList(){
        Wrapper<ServeType> wrapper = new EntityWrapper<>();
        wrapper.eq("status","1");
        int count = this.selectCount(wrapper);
        List<ServeType> serveTypeList = this.selectList(wrapper);
        return Rt.ok(count,serveTypeList);
    }

}
