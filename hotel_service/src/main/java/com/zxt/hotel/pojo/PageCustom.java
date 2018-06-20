package com.zxt.hotel.pojo;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/6/15 15:36
 */
public class PageCustom {
    private Integer index;
    private Integer size;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageCustom{" +
                "index=" + index +
                ", size=" + size +
                '}';
    }
}
