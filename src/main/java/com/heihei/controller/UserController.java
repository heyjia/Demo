package com.heihei.controller;

import com.heihei.entity.User;
import com.heihei.entity.UserAndRole;
import com.heihei.result.CodeMsg;
import com.heihei.result.Result;
import com.heihei.services.UserService;
import com.heihei.services.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
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
import java.util.List;
/**
 * UserController
 * @Description  用户的controller，主要是处理show页面的相关请求
 * @author CHENZEJIA
 * @date 2019/12/17
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    // /user/userList 请求，跳转到用户个人资料页面，从缓存中获取用户的信息
    @RequestMapping (value = "/userList",produces = "text/html")
    @ResponseBody
    public String userList(Model model, HttpServletRequest request, HttpServletResponse response){
        logger.info("个人资料...");
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("userSession");
        logger.info(user.toString());
        model.addAttribute("user",user);
        WebContext webContext = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        String html = thymeleafViewResolver.getTemplateEngine().process("user_list",webContext);
        return html;
    }

    //  /user/update 用户修改自己的资料，从表单回获取相关的数据，更新缓存
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Boolean> update(@RequestParam String birthday, @RequestParam int userSex, @RequestParam String telephone) throws ParseException {
        logger.info("修改个人资料...");
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("userSession");
        user.setUserSex(userSex);
        user.setTelephone(telephone);
        //将生日的String转换成yyyy-MM-dd格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if(birthday != null && birthday.length() > 0) {
            Date date = null;
            try{
                 date = formatter.parse(birthday);
            }catch (Exception e) {
                return Result.error(CodeMsg.BIRTHDAY_ERROR);
            }
            user.setBirthday(date);
        }
        Boolean result = userService.updateUser(user);      //更新用户
        if (result == false){
            return Result.error(CodeMsg.UPDATE_ERROR);
        }
        SecurityUtils.getSubject().getSession().setAttribute("userSession",user);  //更新缓存
        return Result.success(true);
    }

    // /user/updatePassword 修改用户的密码，在缓存中获取用户的信息，并且判断密码是否正确，更新缓存
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public Result<Boolean> updatePassword(@RequestParam String oldPassword,@RequestParam String  newPassword) {
        logger.info("修改密码...");
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("userSession");
        logger.info(user.toString());
        if (!oldPassword .equals(user.getPassword())) {
            logger.info("旧密码错误...");
            return Result.error(CodeMsg.OLDPASSWORD_ERROR);
        }
        boolean result = userService.updatePassword(newPassword,user.getUserId());   //数据库更新密码
        if (result == false) {
            return Result.error(CodeMsg.UPDATEPASSWORD_ERROR);
        }
        user.setPassword(newPassword);
        SecurityUtils.getSubject().getSession().setAttribute("userSession",user);   //更新缓存
        return Result.success(true);
    }

    ///user/toUpdatePassword，前往密码修改页面
    @RequestMapping(value = "/toUpdatePassword",produces = "text/html")
    @ResponseBody
    public String toUpdatePassword(Model model,HttpServletResponse response,HttpServletRequest request){
        logger.info("前往密码修改页面");
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("userSession");
        logger.info(user.toString());
        model.addAttribute("user",user);
        WebContext webContext = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        String html = thymeleafViewResolver.getTemplateEngine().process("user_password_update",webContext);
        return html;
    }
    //admin的话，前往用户管理页面
    @RequestMapping(value = "/toUserManager",produces = "text/html")
    @ResponseBody
    public String toUserManager(Model model,HttpServletResponse response,HttpServletRequest request){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("userSession");
        model.addAttribute("user",user);
        List<UserAndRole> allUsers = userService.selectAllUser();
        model.addAttribute("allUsers",allUsers);
        WebContext webContext = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        String html = thymeleafViewResolver.getTemplateEngine().process("userManager",webContext);
        return html;
    }
}
