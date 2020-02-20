package com.cxy.controller;

import com.cxy.pojo.SysUser;
import com.cxy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName SysUserController
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/2/18 9:18
 */
@Controller
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("/findAll")
    @ResponseBody
    public List<SysUser> aa(){
        return sysUserService.findAll();
    }
}