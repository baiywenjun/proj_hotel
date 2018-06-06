package com.zxt.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/6/1 10:06
 */
public class GenNo {

    /**
     * 酒店订单生成
     * @param username username
     * @return no
     */
    public static String hotelOrderNo(String username){
        return generatorByTimestamp("H",username);
    }

    private static String generatorByTimestamp(String flag, String username){
        long currentMs = System.currentTimeMillis();
        // 获取用户后四位尾数
        String shortCode = null;
        if (StringUtils.isBlank(username)){
            shortCode = "0000";
        }else {
            shortCode = username.substring(username.length() - 4, username.length());
        }
        String timeStamp = Long.toString(currentMs);
        return flag + timeStamp + shortCode;
    }
}
