package com.cxy.service.impl;

import com.cxy.dao.SysRoleMapper;
import com.cxy.pojo.SysRole;
import com.cxy.service.RoleService;
import com.cxy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/2/20 15:29
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public List<String> findRolsByUserID(Long userId) {
        return sysRoleMapper.findRolesByUserId(userId);
    }
}