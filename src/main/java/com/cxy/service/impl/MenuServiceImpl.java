package com.cxy.service.impl;

import com.cxy.dao.SysMenuMapper;
import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.pojo.SysMenu;
import com.cxy.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MenuServiceImpl
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/2/18 13:47
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public DataGridResult findMenu(QueryDTO queryDTO) {
        //设置每页显示几条
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        if(queryDTO.getSort()!=null&&!queryDTO.getSort().equals("")){
            queryDTO.setSort("menu_id");
        }
        List<SysMenu> menuByPage = sysMenuMapper.findMenuByPage(queryDTO);
        PageInfo<SysMenu> info = new PageInfo<SysMenu>(menuByPage);
        System.out.println(info);
        System.out.println("**************************");
        long total = info.getTotal();
        //返回多少行info.getList()，返回总行数total
        DataGridResult result = new DataGridResult(total,info.getList());
        return result;
    }
}