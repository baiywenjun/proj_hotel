package com.zxt.hotel.mapper;

import com.zxt.hotel.entity.SysDept;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author wenjun
 * @since 2018-06-29
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);
}
