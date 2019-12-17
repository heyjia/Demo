package com.heihei.entity;

import java.util.Date;
/**
 * User
 * @Description 用户实体类，与数据库字段对应
 * @author CHENZEJIA
 * @date 2019/12/17
 */
public class User {
    private int userId;
    private String userName;
    private int userSex;    //0为男性 1为女性
    private String password;
    private String salt;
    private String telephone;
    private Date birthday;
    private Date registerTime;

    public User() {
    }

    public User(int userId, String userName, int userSex, String password, String salt, String telephone, Date birthday, Date registerTime) {
        this.userId = userId;
        this.userName = userName;
        this.userSex = userSex;
        this.password = password;
        this.salt = salt;
        this.telephone = telephone;
        this.birthday = birthday;
        this.registerTime = registerTime;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", registerTime=" + registerTime +
                '}';
    }
}
