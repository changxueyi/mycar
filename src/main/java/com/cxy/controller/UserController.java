package com.cxy.controller;

import com.cxy.dto.UserDTO;
import com.cxy.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/2/18 9:48
 */
@Controller
public class UserController {
    @RequestMapping("/sys/login")
    @ResponseBody
    public R login(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getUsername());
        return R.ok();
    }
}