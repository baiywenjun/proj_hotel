package com.zxt.common.util;

/**
 * Title: 统一分页处理工具
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/18 17:16
 */
public class PageUtil {
    public static class PageDomain {
        private Integer page;
        private Integer limit;
        public Integer getPage() {
            return page;
        }
        public void setPage(Integer page) {
            this.page = page;
        }
        public Integer getLimit() {
            return limit;
        }
        public void setLimit(Integer limit) {
            this.limit = limit;
        }
    }

    /**
     * 统一处理分页
     * @param page 页码
     * @param limit 条数
     * @return PageDomain
     */
    public static PageDomain handle(Integer page, Integer limit){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPage(page);
        pageDomain.setLimit(limit);
        // 默认第1页20条
        if(page==null || page<1){
            pageDomain.setPage(1);
        }
        if(limit==null || limit<1){
            pageDomain.setLimit(20);
        }
        return pageDomain;
    }
}
