package com.heihei.services.impl;

import com.heihei.dao.PrivilegeDao;
import com.heihei.entity.Privilege;
import com.heihei.entity.RolePrivilege;
import com.heihei.services.PrivilegeService;
import com.heihei.services.RolePrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * PrivilegeService
 * @Description         权限的service层
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    RolePrivilegeService rolePrivilegeService;
    @Autowired
    PrivilegeDao privilegeDao;
    /*
     * @Description   根据角色的id获取用户的权限
     * @Author CHENZEJIA
     * @Date 2019/12/17
     * @Param [roleId]
     * @return java.util.List<com.heihei.entity.Privilege>
     **/
    public List<Privilege> selectPrivilegeByRoleId(int roleId){
        List<RolePrivilege> rolePrivileges = rolePrivilegeService.selectRolePrivilegeByRoleId(roleId);   //根据角色id查询用户的角色权限中间表
        List<Privilege>  privileges = new ArrayList<>();
        for (RolePrivilege rolePrivilege : rolePrivileges){
            Privilege privilege = privilegeDao.selectPrivilegeByPrivilegeId(rolePrivilege.getPrivilegeId());   //根据权限id查询权限
            privileges.add(privilege);
        }
        return privileges;
    }
}
