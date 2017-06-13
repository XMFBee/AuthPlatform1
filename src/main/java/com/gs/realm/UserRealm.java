package com.gs.realm;

import com.gs.bean.User;
import com.gs.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/3.
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登陆的账号，账号通常为email。
        String email=principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
        Set<String> roleName = userService.queryRoles(email);
        Set<String> permissions = userService.queryPermissions(email) ;
        info.setRoles(roleName);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String email=authenticationToken.getPrincipal().toString();
        User user=userService.queryByEmail(email);
        if(user!=null){
            AuthenticationInfo authenticationInfo;
            if(user.getUserEmail() != null && user.getUserEmail() != "") {
                authenticationInfo = new SimpleAuthenticationInfo(user.getUserEmail(), user.getUserPwd(), "user");
                return authenticationInfo;
            }else{
                authenticationInfo = new SimpleAuthenticationInfo(user.getUserPhone(), user.getUserPwd(), "user");
                return authenticationInfo;
            }
        }else{
            return null;
        }
    }
}
