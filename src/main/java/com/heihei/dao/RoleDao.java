package com.heihei.dao;

import com.heihei.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/**
 * RoleDao
 * @Description  对应数据库的role表 dao层
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Mapper
public interface RoleDao {
    @Select("select * from role where role_id = #{roleId}")
    public Role selectRoleByRoleId(@Param("roleId") int roleId);
}
