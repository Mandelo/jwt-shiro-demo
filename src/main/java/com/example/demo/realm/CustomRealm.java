package com.example.demo.realm;

import com.example.demo.dao.RoleMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/19
 */

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * doGetAuthorizationInfo 授权
     *
     * @param principalCollection principalCollection
     * @return {@link AuthorizationInfo}
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("----------------------权限认证-----------------------");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo ();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        List<String> roleList = roleMapper.getRoleListByUsername(username);
        Set<String> roleSet = new HashSet<>(roleList);
        info.setRoles(roleSet);
        return info;
    }

    /**
     * doGetAuthenticationInfo 登录认证操作
     *
     * @param authenticationToken authenticationToken
     * @return {@link AuthenticationInfo}
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------------------------身份认证方法----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        UserEntity userEntity = userService.selectByUsername(username);
        if(userEntity == null){
            throw new AccountException("用户名不存在");
        }else{
            String password = userEntity.getPassword();
            if(!password.equals(new String((char[])token.getCredentials()))){
                throw new AccountException("密码不正确");
            }else{
                return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
            }
        }
    }
}
