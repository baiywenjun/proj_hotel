package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.entity.SysRole;

import java.util.List;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/7/5 15:35
 */
public class SysAdminExtVO extends SysAdmin {

    private String deptName;

    private List<SysRole> sysRoleList;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }

    @Override
    public String toString() {
        return "SysAdminExtVO{" +
                "deptName='" + deptName + '\'' +
                ", sysRoleList=" + sysRoleList +
                '}';
    }
}
