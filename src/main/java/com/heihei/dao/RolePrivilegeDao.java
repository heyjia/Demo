package com.heihei.dao;

import com.heihei.entity.RolePrivilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * RolePrivilegeDao
 * @Description     对应数据库的role privilege表 dao层
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Mapper
public interface RolePrivilegeDao {

    @Select("select * from role_privilege where role_id = #{roleId}")
    public List<RolePrivilege> selectRolePrivilegeByRoleId(@Param("roleId") int roleId);
}
