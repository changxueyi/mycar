package com.cxy.dto;

import java.util.List;

public class DataGridResult {
   //返回记录数
    private long total;
    //返回多少行
    private List<?> rows;

    DataGridResult(){}

    public DataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
