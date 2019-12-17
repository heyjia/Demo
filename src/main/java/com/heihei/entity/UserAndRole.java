package com.heihei.entity;

import java.util.Date;
import java.util.List;
/**
 * UserAndRole
 * @Description     用户实体类，含有其角色的信息
 * @author CHENZEJIA
 * @date 2019/12/17
 */
public class UserAndRole {
    private int userId;
    private String userName;
    private int userSex;    //0为男性 1为女性
    private String password;
    private String salt;
    private String telephone;
    private Date birthday;
    private Date registerTime;
    private List<Role> roles;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserSex() {
        return userSex;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getTelephone() {
        return telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public UserAndRole() {
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserAndRole{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", registerTime=" + registerTime +
                ", roles=" + roles +
                '}';
    }
}
