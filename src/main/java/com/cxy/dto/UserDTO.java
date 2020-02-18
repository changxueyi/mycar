package com.cxy.dto;

/**
 * @ClassName UserDTO
 * @Description 用户登录的DTO，就是前后天传递数据的中枢
 * @Author changxueyi
 * @Date 2020/2/18 9:46
 */
public class UserDTO {
    private String username;
    private String password;
    //验证码
    private String captcha;
    private boolean rememberMe;//记住我

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}