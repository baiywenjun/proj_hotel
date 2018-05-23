package com.zxt.api.pojo;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/22 14:27
 */
public class OpenidInfo {
    private String sessionKey;
    private String openid;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "OpenidInfo{" +
                "sessionKey='" + sessionKey + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
