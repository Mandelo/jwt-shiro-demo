package com.example.demo.realm;

import com.alibaba.fastjson.JSON;
import com.example.demo.token.JWTToken;
import com.example.demo.token.MyAuth;
import com.example.demo.util.JWTUtil;
import com.example.demo.util.RedisUtil;
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
import java.util.Set;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/22
 */

@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private RedisUtil redisUtil;

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
        String authString = JWTUtil.getAuthString(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //从redis中获取权限信息
        MyAuth auth = JSON.parseObject(authString,MyAuth.class);
        assert auth != null;
        Set<String> roleSet = new HashSet<>(auth.getRoleList());
        Set<String> permSet = new HashSet<>(auth.getPermList());
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
        // 解密获得权限对象
        String authString = JWTUtil.getAuthString(token);
        MyAuth auth = JSON.parseObject(authString, MyAuth.class);
        if (auth == null || !JWTUtil.verify(token, authString)) {
            throw new AuthenticationException("token认证失败！");
        } else if(redisUtil.get(auth.getUsername()) == null){
            throw new AuthenticationException("token过期！");
        }else {
            return new SimpleAuthenticationInfo(token, token, "MyRealm");
        }
    }
}
