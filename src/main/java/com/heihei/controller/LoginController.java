package com.heihei.controller;

import com.heihei.entity.RegisterUserForm;
import com.heihei.entity.User;
import com.heihei.result.CodeMsg;
import com.heihei.result.Result;
import com.heihei.services.UserService;
import com.heihei.services.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * LoginController
 * @Description      登录controller
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    ThymeleafViewResolver thymleafViewResolver;
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    //拦截用户请求，跳转到登录页面
    @RequestMapping("/to_login")
    public String login(){
        return "login";
    }
    //拦截用户登录请求，用shiro进行认证，判断用户是否存在以及用户密码是否正确
    @RequestMapping(value = "/do_login")
    @ResponseBody
    public Result<Boolean> dologin(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String password){
        logger.info("进入dologin");
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);    //将用户名包装成一个token
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);                                                     //执行登录请求，会被shiro过滤器拦截，对用户信息进行认证
        }catch (IncorrectCredentialsException ice){
            return  Result.error(CodeMsg.PASSWORD_ERROR);
        }catch (UnknownAccountException uae){
            return  Result.error(CodeMsg.UNKNOWACCOUNT);
        }catch (Exception e) {
            //其他错误登录异常
            return Result.error(new CodeMsg(10003,e.getMessage()));
        }
        if (subject.isAuthenticated()) {
            logger.info("登录成功");
        }
        return Result.success(true);
    }
    //登录成功，跳转到显示页面，并且在session中获取用户的信息返回,通过thymleaf视图解析器返回
    @RequestMapping(value = "/show",produces = "text/html")
    @ResponseBody
    public String show(Model model, HttpServletRequest request, HttpServletResponse response){
        logger.info("show方法");
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("userSession");
        model.addAttribute("user",user);
        WebContext ctx = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        String html = thymleafViewResolver.getTemplateEngine().process("show",ctx);
        return html;
    }
    //登出请求
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Result<Boolean> logout() {
        Subject subject = SecurityUtils.getSubject();     //logout登录
        subject.logout();
        if (subject.isAuthenticated()){
            return Result.error(CodeMsg.LOGOUT_ERROR);
        }
        return Result.success(true);
    }
    //前往注册页面
    @RequestMapping (value = "/toregister")
    public String toRister(){
        logger.info("前往注册页面");
        return "register";
    }
    //注册请求，获取表单中的注册信息，将表单的信息封装成一个user，判断用户是否存在，然后事务添加用户以及其角色 默认User角色
    @RequestMapping (value = "/register")
    @ResponseBody
    public Result<Boolean> register(RegisterUserForm registerUserForm) throws ParseException {
        logger.info("注册");
        logger.info(registerUserForm.toString());
        User user = new User();
        user.setUserName(registerUserForm.getUserName());
        user.setPassword(registerUserForm.getNewPassword());
        user.setTelephone(registerUserForm.getTelephone());
        user.setUserSex(registerUserForm.getSex());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (registerUserForm.getBirthday() != null && registerUserForm.getBirthday().length() > 0) {
            Date date = formatter.parse(registerUserForm.getBirthday());
            user.setBirthday(date);
        }
        user.setRegisterTime(new Date());
        User tempUser = userService.selectUserByUserName(user.getUserName());    //根据用户名查询用户，判断用户是否存在
        if (tempUser != null) {
            logger.info("用户已存在");
            return Result.error(CodeMsg.USER_EXISTED);
        }
        boolean addUserResult =  userService.addUser(user,registerUserForm.getRoleId());    //添加用户
        if (addUserResult == false) {
            return Result.error(CodeMsg.REGISTER_ERROR);
        }else{
            return Result.success(true);
        }
    }
}
