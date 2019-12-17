package com.heihei.entity;
/**
 * UserRole
 * @Description     用户角色中间表，与数据库对应
 * @author CHENZEJIA  
 * @date 2019/12/17
 */
public class UserRole {
    private int id;
    private int userId;
    private int roleId;

    public UserRole() {
    }

    public UserRole(int id, int userId, int roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
