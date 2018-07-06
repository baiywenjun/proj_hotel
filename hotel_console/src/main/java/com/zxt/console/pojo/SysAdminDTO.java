package com.zxt.console.pojo;

import com.zxt.hotel.entity.SysAdmin;

import java.util.List;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/7/6 14:58
 */
public class SysAdminDTO extends SysAdmin {

    private List<Long> roleIdList;

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Override
    public String toString() {
        return "SysAdminDTO{" +
                "roleIdList=" + roleIdList +
                '}';
    }
}
