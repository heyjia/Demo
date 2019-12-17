package com.heihei.services;

import com.heihei.dao.UserDao;
import com.heihei.entity.Role;
import com.heihei.entity.User;
import com.heihei.entity.UserAndRole;
import com.heihei.services.impl.RoleServiceImpl;
import com.heihei.services.impl.UserRoleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * UserService
 * @Description 用户的service层接口
 * @author CHENZEJIA
 * @date 2019/12/17
 */
public interface UserService {
    //根据用户名查询用户
    public User selectUserByUserName(String userName);
    //更新用户信息
    public boolean updateUser(User user) ;
    //修改用户密码
    public boolean  updatePassword(String newPassword,int userId);
    //添加用户，事务，先添加用户，并且默认给他User的角色
    @Transactional
    public boolean addUser(User user,int roleId) ;
    //查询所有的用户，以及其对应的角色
    public List<UserAndRole> selectAllUser();
    //根据用户获取其对应的角色
    public List<UserAndRole> getUserAndRolePerUser(List<User> users);
    //事务删除用户，先删除用户角色，再删除用用户的信息
    @Transactional
    public boolean deleteUser(int userId);
    //根据查询条件模糊查询用户
    public List<UserAndRole> selectAllUserByTip(String selectTip);
}
