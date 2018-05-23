package com.zxt.api.pojo;

import com.zxt.hotel.entity.SysUser;

/**
 * Title: TokenInfo
 * Description: 可以获取openid与用户信息
 * author: wenjun
 * date: 2018/5/23 11:38
 */
public class TokenInfo {
    private OpenidInfo openidInfo;
    private SysUser sysUser;

    public OpenidInfo getOpenidInfo() {
        return openidInfo;
    }

    public void setOpenidInfo(OpenidInfo openidInfo) {
        this.openidInfo = openidInfo;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "openidInfo=" + openidInfo +
                ", sysUser=" + sysUser +
                '}';
    }
}
