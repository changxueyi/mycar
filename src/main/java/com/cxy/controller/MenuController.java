package com.cxy.controller;

import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.pojo.SysMenu;
import com.cxy.service.MenuService;
import com.cxy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    /*删除菜单*/
    @RequestMapping("/sys/menu/del")
    @ResponseBody
    public R deleteMenu(@RequestBody List<Long> ids){
        return menuService.deleteMenu(ids);
    }
    //新增菜单树形结构
    @RequestMapping("/sys/menu/select")
    @ResponseBody
    public R selectMenu(){
        return menuService.selectMenu();
    }
    @RequestMapping("/sys/menu/save")
    @ResponseBody
    public R saveMenu(@RequestBody SysMenu sysMenu){
        return menuService.saveMenu(sysMenu);
    }

    //修改菜单
    @RequestMapping("/sys/menu/info/{menuId}")
    @ResponseBody
    public R findMenuBuId(@PathVariable("menuId") Long menuId){
        return menuService.findMenuById(menuId);
    }
    @RequestMapping("/sys/menu/update")
    @ResponseBody
    public R updateMenu(@RequestBody SysMenu sysMenu){
        return menuService.updateMenu(sysMenu);
    }

}