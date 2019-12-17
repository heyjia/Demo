package com.heihei.dao;

import com.heihei.entity.Privilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/**
 * PrivilegeDao
 * @Description    对应数据库的privilege表 dao层
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Mapper
public interface PrivilegeDao {

    @Select("select * from privilege where privilege_id = #{privilegeId}")
    public  Privilege selectPrivilegeByPrivilegeId(@Param("privilegeId") int privilegeId);
}
