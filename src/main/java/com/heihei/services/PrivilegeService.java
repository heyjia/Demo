package com.heihei.services;

import com.heihei.dao.PrivilegeDao;
import com.heihei.entity.Privilege;
import com.heihei.entity.RolePrivilege;
import com.heihei.services.impl.RolePrivilegeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * PrivilegeService
 * @Description         权限的service层接口
 * @author CHENZEJIA
 * @date 2019/12/17
 */
public interface PrivilegeService {
    public List<Privilege> selectPrivilegeByRoleId(int roleId);
}
