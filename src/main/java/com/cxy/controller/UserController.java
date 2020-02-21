package com.cxy.controller;

import com.cxy.dto.DataGridResult;
import com.cxy.dto.QueryDTO;
import com.cxy.dto.UserDTO;
import com.cxy.pojo.SysUser;
import com.cxy.service.SysUserService;
import com.cxy.utils.MD5Utils;
import com.cxy.utils.R;
import com.cxy.utils.ShiroUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
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
    @Autowired
    private DefaultKaptcha kaptcha;
    @RequestMapping("sys/login")
    @ResponseBody
    public R login(@RequestBody UserDTO userDTO){
       //对比验证码
        String severKaptcha = ShiroUtils.getKaptcha();
        if (!severKaptcha.equalsIgnoreCase(userDTO.getCaptcha())){
            return R.error("验证码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        //md5中的参数1.原始密码，2.盐，3.加盐次数
       String newPass = MD5Utils.md5(userDTO.getPassword(),userDTO.getUsername(),1024);
        UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUsername(),newPass);
        if(userDTO.isRememberMe()){
            token.setRememberMe(true);
        }
        subject.login(token);
        //会去调用自定义的realm
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

    //登录验证码
    @RequestMapping("/captcha.jpg")
    public void capycha(HttpServletResponse response) {
        // 缓存设置-设置不缓存（可选操作）
        response.setHeader("Cache-Control", "no-store, no-cache");
        // 设置响应内容
        response.setContentType("image/jpg");
        //生成验证码
        String text = kaptcha.createText();//文本
        //生成图片
        BufferedImage image = kaptcha.createImage(text);
        //验证码存储到shiro的 session
        ShiroUtils.setKaptcha(text);
        try {
            //返回到页面
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //使用shiro实现  登出
    @RequestMapping("/logout")
    public String logout(){
        ShiroUtils.logout();
        return "redirect:login.html";
    }
    @RequestMapping("/sys/user/info")
    @ResponseBody
    public R userinfo(){
        //可以从shiro中获取
        SysUser userEntity = ShiroUtils.getUserEntity();
        return R.ok().put("user",userEntity);
    }

}