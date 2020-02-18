package com.cxy.service;

import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.pojo.SysMenu;

import java.util.List;

public interface MenuService {
    public DataGridResult findMenu(QueryDTO queryDTO);
}
