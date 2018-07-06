package com.zxt.hotel.service;

import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtils;
import com.zxt.hotel.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-06-29
 */
public interface SysRoleService extends IService<SysRole> {

    Rt queryListByPage(Integer page, Integer limit);

    PageUtils queryPage(Map<String, Object> params);

    void save(SysRole role);

    void update(SysRole role);

    void deleteBatch(Long[] roleIds);
}
