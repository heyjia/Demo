package com.heihei.entity;
/**
 * RolePrivilege
 * @Description    角色权限中间表，与数据库对应
 * @author CHENZEJIA
 * @date 2019/12/17
 */
public class RolePrivilege {
    private int id;
    private int roleId;
    private int privilegeId;
    public RolePrivilege(int id, int roleId, int privilegeId) {
        this.id = id;
        this.roleId = roleId;
        this.privilegeId = privilegeId;
    }

    public RolePrivilege() {
    }

    public int getId() {
        return id;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }
}
