package com.heihei.entity;

/**
 * RegisterUserForm
 * @Description     注册用户信息的实体类
 * @author CHENZEJIA  
 * @date 2019/12/17
 */
public class RegisterUserForm {
    private int roleId;
    private String userName;
    private String newPassword;
    private int sex;
    private String telephone;
    private String birthday;

    public RegisterUserForm() {
    }

    public RegisterUserForm(int roleId, String userName, String newPassword, int sex, String telephone, String birthday) {
        this.roleId = roleId;
        this.userName = userName;
        this.newPassword = newPassword;
        this.sex = sex;
        this.telephone = telephone;
        this.birthday = birthday;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
