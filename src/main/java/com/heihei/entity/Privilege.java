package com.heihei.entity;
/**
 * Privilege
 * @Description 权限实体类，与数据库字段对应
 * @author CHENZEJIA
 * @date 2019/12/17
 */
public class Privilege {
    private int privilegeId;
    private String privilegeName;

    public Privilege() {
    }

    public Privilege(int privilegeId, String privilegeName) {
        this.privilegeId = privilegeId;
        this.privilegeName = privilegeName;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public int getPrivilegeId() {
        return privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }
}
