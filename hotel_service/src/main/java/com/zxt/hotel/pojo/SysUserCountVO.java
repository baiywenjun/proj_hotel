package com.zxt.hotel.pojo;

/**
 * Title: 用户统计视图
 * Description: todoedit
 * author: wenjun
 * date: 2018/6/8 15:45
 */
public class SysUserCountVO {
    private Integer total;
    private Integer monthCount;
    private Integer weekCount;
    private Integer dayCount;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public Integer getWeekCount() {
        return weekCount;
    }

    public void setWeekCount(Integer weekCount) {
        this.weekCount = weekCount;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }
}
