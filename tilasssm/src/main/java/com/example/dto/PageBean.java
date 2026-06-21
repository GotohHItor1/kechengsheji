package com.example.dto;

import java.util.List;

/**
 * 分页查询结果封装
 */
public class PageBean {
    /** 总记录数 */
    private Long total;
    /** 当前页数据列表 */
    private List<?> rows;

    public PageBean() {}

    public PageBean(Long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() { return total; }
    public void setTotal(Long total) { this.total = total; }
    public List<?> getRows() { return rows; }
    public void setRows(List<?> rows) { this.rows = rows; }

    @Override
    public String toString() {
        return "PageBean{total=" + total + ", rows.size=" + (rows != null ? rows.size() : 0) + '}';
    }
}
