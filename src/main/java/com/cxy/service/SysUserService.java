package com.cxy.service;

import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.pojo.SysUser;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface SysUserService {
    public List<SysUser> findAll();
    public DataGridResult findUserByPage(QueryDTO queryDTO);
    /**
     * 导出
     * @param
     * @return org.apache.poi.ss.usermodel.Workbook
    */
    public Workbook exportUser();
    //根据用户名查询
    public SysUser findByUsername(String username);
}
