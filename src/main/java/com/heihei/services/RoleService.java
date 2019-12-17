package com.heihei.services;

import com.heihei.dao.RoleDao;
import com.heihei.entity.Role;
import com.heihei.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * RoleService
 * @Description    角色的service层
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Service
public class RoleService {

    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleDao roleDao;
    //根据用户的id查询用户的角色
    public List<Role> selectRolesByUserId(int userId) {
        List<UserRole> userRoles = userRoleService.selectUserRoleByUserId(userId);    //根据用户的id，先查询中间表
        List<Role> roles = new ArrayList<>();
        for (UserRole tempRole : userRoles ) {                                      //再根据中间表获取的角色id查询角色
            Role role = roleDao.selectRoleByRoleId(tempRole.getRoleId());
            roles.add(role);
        }
        return roles;
    }
}
