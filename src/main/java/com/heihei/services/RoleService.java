package com.heihei.services;

import com.heihei.dao.RoleDao;
import com.heihei.entity.Role;
import com.heihei.entity.UserRole;
import com.heihei.services.impl.UserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * RoleService
 * @Description    角色的service层接口
 * @author CHENZEJIA
 * @date 2019/12/17
 */
public interface RoleService {
    //根据用户的id查询用户的角色
    public List<Role> selectRolesByUserId(int userId);
}
