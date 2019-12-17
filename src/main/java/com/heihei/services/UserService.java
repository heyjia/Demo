package com.heihei.services;

import com.heihei.dao.UserDao;
import com.heihei.entity.Role;
import com.heihei.entity.User;
import com.heihei.entity.UserAndRole;
import com.heihei.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * UserService
 * @Description 用户的service层
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDao userDao;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleService roleService;
    //根据用户名查询用户
    public User selectUserByUserName(String userName) {
        return userDao.selectUserByUserName(userName);
    }
    //更新用户信息
    public boolean updateUser(User user) {
        int temp  = userDao.updateUser(user);
        logger.info(temp+"");
        if (temp <= 0) {
            return false;
        }
        return true;
    }
    //修改用户密码
    public boolean  updatePassword(String newPassword,int userId) {
        int temp = userDao.updatePassword(newPassword,userId);
        if (temp <= 0) {
            return false;
        }
        return true;
    }
    //添加用户，事务，先添加用户，并且默认给他User的角色
    @Transactional
    public boolean addUser(User user) {
        int userId = userDao.addUser(user);     //在User表中添加用户
        if (userId > 0) {
            boolean addresult = userRoleService.addUserRole(user.getUserId(),3);      //User_Role表，添加用户的角色
            if (addresult == false) {
                return false;
            }
            return true;
        }else{
            return false;
        }
    }
    //查询所有的用户，以及其对应的角色
    public List<UserAndRole> selectAllUser() {
        List<User> users = userDao.selectAllUser();
        List<UserAndRole> allUsers = getUserAndRolePerUser(users);
        return allUsers;
    }
    //根据用户获取其对应的角色
    public List<UserAndRole> getUserAndRolePerUser(List<User> users){
        List<UserAndRole> allUsers = new ArrayList<>();
        for (User user : users) {
            List<Role> roles = roleService.selectRolesByUserId(user.getUserId());    //根据用户id获取其对应的角色
            UserAndRole userAndRole = new UserAndRole();
            userAndRole.setUserId(user.getUserId());
            userAndRole.setBirthday(user.getBirthday());
            userAndRole.setPassword(user.getPassword());
            userAndRole.setRegisterTime(user.getRegisterTime());
            userAndRole.setRoles(roles);
            userAndRole.setSalt(user.getSalt());
            userAndRole.setTelephone(user.getTelephone());
            userAndRole.setUserSex(user.getUserSex());
            userAndRole.setUserName(user.getUserName());
            allUsers.add(userAndRole);
        }
        return allUsers;
    }
    //事务删除用户，先删除用户角色，再删除用用户的信息
    @Transactional
    public boolean deleteUser(int userId) {
        boolean deleteUserRoleResult = userRoleService.deleteUserRole(userId);    //删除用户角色
        if (deleteUserRoleResult == false) {
            return false;
        }
        int id = userDao.deleteUser(userId);                    //删除用户信息
        if (id > 0){
            return true;
        }
        return false;
    }
    //根据查询条件模糊查询用户
    public List<UserAndRole> selectAllUserByTip(String selectTip) {
        List<User> users = userDao.selectUserBySelectTip(selectTip);   //模糊查询用户
        List<UserAndRole> allUsers = getUserAndRolePerUser(users);      //对每个用户查询用户的角色
        return allUsers;
    }
}
