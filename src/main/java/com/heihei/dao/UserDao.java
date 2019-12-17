package com.heihei.dao;

import com.heihei.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
/**
 * UserDao
 * @Description    对应数据库的user表 dao层
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Mapper
public interface UserDao {

    @Select("select * from user where user_name = #{userName}")
    public User selectUserByUserName(@Param("userName") String userName);

    @Update("update user u set u.user_sex = #{userSex},u.telephone = #{telephone},u.birthday = #{birthday} where u.user_id = #{userId}")
    public int updateUser(User user);
    @Update("update user u set u.password = #{newPassword} where u.user_id = #{userId}")
    public int updatePassword(@Param("newPassword") String newPassword,@Param("userId") int userId);

    @Insert("insert into user(user_name,user_sex,password,salt,telephone,birthday,register_time) values (#{userName},#{userSex},#{password},#{salt},#{telephone},#{birthday},#{registerTime})")
    @SelectKey(keyColumn="user_id", keyProperty="userId", resultType=int.class, before=false, statement="select last_insert_id()")
    public int addUser(User user);

    @Select("select * from user")
    List<User> selectAllUser();

    @Delete("delete from user where user_id = #{userId}")
    public int deleteUser(@Param("userId") int userId);

    @Select("select * from user where user_name like concat('%',#{selectTip},'%')")
    public List<User> selectUserBySelectTip(@Param("selectTip") String selectTip);
}
