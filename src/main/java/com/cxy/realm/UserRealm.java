package com.cxy.realm;

import com.cxy.pojo.SysUser;
import com.cxy.service.MenuService;
import com.cxy.service.RoleService;
import com.cxy.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName UserRealm
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/2/20 16:16
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        Long userId = sysUser.getUserId();
        //通过用户id查询用户角色
        List<String> rolsByUserID = roleService.findRolsByUserID(userId);
        //查询用户的菜单权限
        List<String> permsByUserId = menuService.findPermsByUserId(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(rolsByUserID);
        info.addStringPermissions(permsByUserId);
        return info;

    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //得到用户名和密码
        //获取当前用户的用户名
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
      String password = new String(usernamePasswordToken.getPassword());
        SysUser byUsername = sysUserService.findByUsername(username);
        if(byUsername==null){
            throw new UnknownAccountException("账户不存在");
        }
        if(!byUsername.getPassword().equals(password)){
            throw new IncorrectCredentialsException("密码不正确");
        }
        if(byUsername.getStatus()==0){
            throw new LockedAccountException("账户被冻结");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(byUsername,password,this.getName());
        return info;
    }
}