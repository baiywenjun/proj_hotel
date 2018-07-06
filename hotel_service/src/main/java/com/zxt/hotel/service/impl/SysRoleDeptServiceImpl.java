package com.zxt.hotel.service.impl;

import com.zxt.hotel.entity.SysRoleDept;
import com.zxt.hotel.mapper.SysRoleDeptMapper;
import com.zxt.hotel.service.SysRoleDeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色与部门对应关系 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-06-29
 */
@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements SysRoleDeptService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //先删除角色与部门关系
        deleteBatch(new Long[]{roleId});

        if(deptIdList.size() == 0){
            return ;
        }

        //保存角色与菜单关系
        List<SysRoleDept> list = new ArrayList<>(deptIdList.size());
        for(Long deptId : deptIdList){
            SysRoleDept sysRoleDeptEntity = new SysRoleDept();
            sysRoleDeptEntity.setDeptId(deptId);
            sysRoleDeptEntity.setRoleId(roleId);

            list.add(sysRoleDeptEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Long> queryDeptIdList(Long[] roleIds) {
        return baseMapper.queryDeptIdList(roleIds);
    }

    @Override
    public int deleteBatch(Long[] roleIds){
        return baseMapper.deleteBatch(roleIds);
    }

}
