package com.example.demo.realm;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.PermService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.token.JWTToken;
import com.example.demo.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/22
 */

@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermService permService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * doGetAuthorizationInfo 授权操作
     *
     * @param principals principals
     * @return {@link AuthorizationInfo}
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String username = JWTUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> roleList = roleService.getRoleListByUsername(username);
        List<String> permList = permService.getPermListByUsername(username);
        Set<String> roleSet = new HashSet<>(roleList);
        Set<String> permSet = new HashSet<>(permList);
        info.setRoles(roleSet);
        info.setStringPermissions(permSet);
        return info;
    }

    /**
     * doGetAuthenticationInfo 登录认证
     *
     * @param authenticationToken authenticationToken
     * @return {@link AuthenticationInfo}
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        } else {
            UserEntity userEntity = userService.selectByUsername(username);
            if (null == userEntity) {
                throw new AuthenticationException("该用户不存在！");
            } else {
                String password = userEntity.getPassword();
                if (userEntity.getBan() == 1) {
                    throw new AuthenticationException("该用户已被封号！");
                } else {
                    return new SimpleAuthenticationInfo(token, token, "MyRealm");
                }
            }
        }
    }
}
