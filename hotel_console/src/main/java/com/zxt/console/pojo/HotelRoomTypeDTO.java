package com.zxt.console.pojo;

import com.zxt.hotel.entity.HotelRoomType;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/6/6 16:11
 */
public class HotelRoomTypeDTO extends HotelRoomType {
    // 房型规格主键
    private Long dictId;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    @Override
    public String toString() {
        return "HotelRoomTypeDTO{" +
                "dictId=" + dictId +
                '}';
    }
}
