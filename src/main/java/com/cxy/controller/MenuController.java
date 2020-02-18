package com.cxy.controller;

import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName MenuController
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/2/18 14:04
 */
@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/sys/menu/list")
    @ResponseBody
    public DataGridResult findMenu(QueryDTO queryDTO){
        return menuService.findMenu(queryDTO);
    }
}