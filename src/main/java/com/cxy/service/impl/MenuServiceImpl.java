package com.cxy.service.impl;

import com.cxy.dao.SysMenuMapper;
import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.pojo.SysMenu;
import com.cxy.service.MenuService;
import com.cxy.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Transactional(propagation = Propagation.REQUIRED)
    public R deleteMenu(List<Long> ids) {
        for (Long id: ids){
            if (id<51){
                return R.error("系统菜单，不支持删除");
            }
        }
        int i = sysMenuMapper.deleteMenu(ids);
        if (i>0){
            return R.ok();
        }else {
            return R.error(400,"删除失败");
        }
    }
    //查询菜单树
    @Override
    public R selectMenu() {
        List<SysMenu> menu = sysMenuMapper.findMenu();
        //添加一个根目录
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(0L);
        sysMenu.setType(0);
        sysMenu.setParentId(-1L);
        sysMenu.setName("一级菜单");
        menu.add(sysMenu);
        return R.ok().put("menuList",menu);
    }

    //新建
    public R saveMenu(SysMenu sysMenu){
        int i =sysMenuMapper.insertSelective(sysMenu);
        return i>0?R.ok():R.error("新增失败");
    }

    @Override
    public R findMenuById(Long menuId) {
        SysMenu sysMenu =sysMenuMapper.selectByPrimaryKey(menuId);
        return R.ok().put("menu",sysMenu);
    }
    //更新
    @Override
    public R updateMenu(SysMenu sysMenu) {
        int i = sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return i>0?R.ok():R.error("修改失败");
    }

    @Override
    public List<String> findPermsByUserId(Long userId) {
       List<String> permsByUserId =  sysMenuMapper.findPermsByUserId(userId);
       Set<String> set = new HashSet<String>();
       for (String s: permsByUserId){
           if (s!=null&&s.equals("")){
               //如果有，符号分割
               String[] split = s.split(",");
               for (String s1 : split){
                   set.add(s1);
               }
           }
       }
        List<String> perms = new ArrayList<>();
       perms.addAll(set);
       return perms;
    }
}