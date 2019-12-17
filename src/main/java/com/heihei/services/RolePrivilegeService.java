package com.heihei.services;

import com.heihei.dao.RolePrivilegeDao;
import com.heihei.entity.RolePrivilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * RolePrivilegeService
 * @Description    角色权限中间表service层
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Service
public class RolePrivilegeService {
    @Autowired
    RolePrivilegeDao rolePrivilegeDao;
    //根据角色id查询角色权限中间表数据
    public List<RolePrivilege> selectRolePrivilegeByRoleId(int roleId) {
        List<RolePrivilege> rolePrivileges = rolePrivilegeDao.selectRolePrivilegeByRoleId(roleId);
        return rolePrivileges;
    }
}
