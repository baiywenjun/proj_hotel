package com.zxt.hotel.service;

import com.zxt.hotel.entity.SysRoleDept;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色与部门对应关系 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-06-29
 */
public interface SysRoleDeptService extends IService<SysRoleDept> {

    void saveOrUpdate(Long roleId, List<Long> deptIdList);

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<Long> queryDeptIdList(Long[] roleIds) ;

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
