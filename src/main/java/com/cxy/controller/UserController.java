package com.cxy.controller;

import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.dto.UserDTO;
import com.cxy.service.SysUserService;
import com.cxy.utils.R;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/2/18 9:48
 */
@Controller
public class UserController {
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("/sys/login")
    @ResponseBody
    public R login(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getUsername());
        return R.ok();
    }
    @RequestMapping("/sys/user/list")
    @ResponseBody
    public DataGridResult findUser1(QueryDTO queryDTO){
        return sysUserService.findUserByPage(queryDTO);
    }

    //导出
    @RequestMapping("/sys/user/export")
    public void exportUser(HttpServletResponse response){
        Workbook workbook = sysUserService.exportUser();
        //设置相应头
        try {
            response.setContentType("applicaiton/octer-stream");
            String fileName = "员工信息.xls";
            fileName = URLEncoder.encode(fileName,"utf-8");
            response.setHeader("content-disposition","attachment;filename="+fileName);
            //文件下载
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}