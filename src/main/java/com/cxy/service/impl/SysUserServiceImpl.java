package com.cxy.service.impl;

import com.cxy.dao.SysUserMapper;
import com.cxy.pojo.SysUser;
import com.cxy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/2/18 9:16
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectByExample(null);
    }
}