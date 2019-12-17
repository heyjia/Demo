package com.heihei.entity;

/**
 * RegisterUserForm
 * @Description     注册用户信息的实体类
 * @author CHENZEJIA  
 * @date 2019/12/17
 */
public class RegisterUserForm {
    private String userName;
    private String newPassword;
    private int sex;
    private String telephone;
    private String birthday;

    public RegisterUserForm() {
    }

    public RegisterUserForm(String userName, String newPassword, int sex, String telephone, String birthday) {
        this.userName = userName;
        this.newPassword = newPassword;
        this.sex = sex;
        this.telephone = telephone;
        this.birthday = birthday;
    }

    public String getUserName() {
        return userName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public int getSex() {
        return sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "RegisterUserForm{" +
                "userName='" + userName + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", sex=" + sex +
                ", telephone='" + telephone + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
