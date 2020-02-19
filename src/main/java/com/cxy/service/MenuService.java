package com.cxy.service;

import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.pojo.SysMenu;
import com.cxy.utils.R;

import java.util.List;

public interface MenuService {
    public DataGridResult findMenu(QueryDTO queryDTO);
    public R deleteMenu(List<Long> ids);
    public R selectMenu();
    public R saveMenu(SysMenu sysMenu);
    public R findMenuById(Long menuId);
    public R updateMenu(SysMenu sysMenu);

}
