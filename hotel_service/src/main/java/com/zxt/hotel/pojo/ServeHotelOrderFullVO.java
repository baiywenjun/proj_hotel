package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.ServeHotelOrder;
import com.zxt.hotel.entity.ServeType;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/6/7 14:35
 */
public class ServeHotelOrderFullVO extends ServeHotelOrder {
    private ServeType serveType;

    public ServeType getServeType() {
        return serveType;
    }

    public void setServeType(ServeType serveType) {
        this.serveType = serveType;
    }

    @Override
    public String toString() {
        return "ServeHotelOrderFullVO{" +
                "serveType=" + serveType +
                '}';
    }
}
