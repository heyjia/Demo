package com.heihei.services;

import com.heihei.dao.UserRoleDao;
import com.heihei.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserRoleService
 * @Description    用户角色service层接口
 * @author CHENZEJIA
 * @date 2019/12/17
 */

public interface UserRoleService {
    //根据用户id查询用户角色中间表数据
    public List<UserRole> selectUserRoleByUserId(int userId);
    //对中间表插入数据，添加用户的角色
    public boolean addUserRole(int userId,int roleId);
    //对中间表删除数据，删除用户的角色
    public boolean deleteUserRole(int userId);
}
