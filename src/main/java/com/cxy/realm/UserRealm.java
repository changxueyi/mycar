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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private SysUserService sysUserService;
    @Autowired
    @Lazy
    private RoleService roleService;
    @Autowired
    @Lazy
    private MenuService menuService;
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //得到用户名和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        System.out.println("*****查询所有*****");
        List<SysUser> a =sysUserService.findAll();
        System.out.println(a);
        System.out.println("*****查询该用户*****");
        SysUser sysUser = sysUserService.findByUsername(username);
        System.out.println(sysUser);
        System.out.println("查询用户成功");
        if(sysUser==null){
            throw new UnknownAccountException("账户不存在");
        }
        if(!sysUser.getPassword().equals(password)){
            throw new IncorrectCredentialsException("密码不正确");
        }
        if(sysUser.getStatus()==0){
            throw new LockedAccountException("账户被冻结");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser,password,this.getName());
        return info;
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser = (SysUser)principals.getPrimaryPrincipal();
        Long userId = sysUser.getUserId();
        //用户的角色
        List<String> rolsByUserID = roleService.findRolsByUserID(userId);
        //用户的菜单权限
        List<String> permsByUserId = menuService.findPermsByUserId(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(rolsByUserID);
        info.addStringPermissions(permsByUserId);
        return info;
    }

}