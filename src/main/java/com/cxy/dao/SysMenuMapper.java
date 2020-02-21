package com.cxy.dao;

import com.cxy.dto.QueryDTO;
import com.cxy.pojo.SysMenu;
import com.cxy.pojo.SysMenuExample;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
    int countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);
    //根据主键id默认进行删除
    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> findMenuByPage(QueryDTO query);
    /*批量删除*/
    int deleteMenu(List<Long> ids);
    //展示编辑，菜单树
    List<SysMenu> findMenu();

    List<String> findPermsByUserId(@Param("userId") Long userId);

    //查询一级菜单

    List<Map<String,Object>> findDirMenuByUserId(@Param("userId") Long userId);
    //查询一级目录对应的菜单

    List<Map<String,Object>> findMenuNotButtonByUserId(@Param("userId") Long userId,@Param("parentId") Long parentId);
}