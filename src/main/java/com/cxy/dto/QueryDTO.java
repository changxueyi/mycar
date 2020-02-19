package com.cxy.dto;

/**
 * @ClassName QueryDTO
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/2/18 10:32
 */
public class QueryDTO {
    //http://localhost:8080/sys/menu/list?order=asc&limit=10&offset=0
    //分页开始位置,比如第一页，offset等于0，当limit等于10，第二页，offset等于10
    private int offset;
    //每页展示的数量，限制
    private int limit;
    private String order;//点击菜单id的排序按钮后，自动升序变降序，传给controller
    //排序的字段，种类，类别
    private String sort;//后台已设置好
    //搜索，模糊搜索
    private String search;//默认为null
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}