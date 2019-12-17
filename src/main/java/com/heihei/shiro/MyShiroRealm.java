package com.heihei.shiro;

import com.heihei.entity.Privilege;
import com.heihei.entity.Role;
import com.heihei.entity.User;
import com.heihei.services.PrivilegeService;
import com.heihei.services.RoleService;
import com.heihei.services.UserService;
import com.heihei.services.impl.PrivilegeServiceImpl;
import com.heihei.services.impl.RoleServiceImpl;
import com.heihei.services.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * MyShiroRealm
 * @Description 定制自己的Realm，定制自己的认证和授权逻辑
 * @author CHENZEJIA
 * @date 2019/12/17
 */
public class MyShiroRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PrivilegeService privilegeService;
    /*
     * @Description 授权方法，当使用到授权注解，或者前端shiro注解，都会执行来执行这个方法，可配置缓存来存储获取到的权限。
     *              查询数据库，用户以及其角色以及角色对应的权限
     * @Author CHENZEJIA
     * @Date 2019/12/17
     * @Param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            logger.info("授权");
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            String userName = (String) principalCollection.getPrimaryPrincipal();
            User user = userService.selectUserByUserName(userName);               //根据用户名查询数据库的用户
            List<Role> roles = roleService.selectRolesByUserId(user.getUserId()); //查询用户对应的角色
            for (Role role : roles) {                                             //遍历用户的角色查询角色对应的权限，addStringPermission，将全放放进去
                simpleAuthorizationInfo.addRole(role.getRoleName());
                logger.info("roleName: " + role.getRoleName());
                List<Privilege> perRolePrivileges = privilegeService.selectPrivilegeByRoleId(role.getRoleId());
                for (Privilege privilege : perRolePrivileges) {
                    simpleAuthorizationInfo.addStringPermission(privilege.getPrivilegeName());
                    logger.info("PrivilegeName: " + privilege.getPrivilegeName());
                }
            }
            return simpleAuthorizationInfo;
    }
    /*
     * @Description 认证方法，在调用subject.login(token)时，会进行认证，主要查询用户是否存在以及密码是否正确
     * @Author CHENZEJIA
     * @Date 2019/12/17
     * @Param [authenticationToken]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String inputUserName = (String) authenticationToken.getPrincipal();
        User user = userService.selectUserByUserName(inputUserName);       //根据用户名查询用户，判断其是否存在
        if(user == null) {
            throw new UnknownAccountException("用户不存在");
        }else{
            logger.info(user.toString());
        }
        //判断密码是否正确，并且将用户放入session缓存
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(inputUserName,user.getPassword(),getName());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession",user);
        return simpleAuthenticationInfo;
    }
}
