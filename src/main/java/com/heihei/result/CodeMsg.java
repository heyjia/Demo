package com.heihei.result;

/**
 * @ClassName CodeMsg
 * @Description 错误时候的结果集
 * @Author CHENZEJIA
 * @Date 2019/12/17 10:57
 **/
public class CodeMsg {

    private int code;
    private String msg;
    //登录模块
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(10001,"密码错误");
    public static CodeMsg UNKNOWACCOUNT = new CodeMsg(10002,"用户不存在");
    public static CodeMsg LOGOUT_ERROR = new CodeMsg(10004,"登录失败");
    public static CodeMsg USER_EXISTED = new CodeMsg(10005,"用户已存在");
    public static CodeMsg REGISTER_ERROR = new CodeMsg(10006,"数据库异常，注册失败");
    //显示模块
    public static CodeMsg UPDATE_ERROR = new CodeMsg(20001,"修改用户资料失败");
    public static CodeMsg OLDPASSWORD_ERROR = new CodeMsg(20002,"旧密码错误");
    public static CodeMsg UPDATEPASSWORD_ERROR = new CodeMsg(20003,"更换密码失败");
    //用户管理
    public static CodeMsg DELETEUSER_ERROR = new CodeMsg(30001,"删除用户失败");
    public static CodeMsg UPDATEUSER_ERROR = new CodeMsg(30002,"更改用户资料失败");
    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
