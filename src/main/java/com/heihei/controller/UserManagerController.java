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
import org.springframework.web.bind.annotation.PathVariable;
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
 * @ClassName UserManagerController
 * @Description 用户管理的controller，拦截usermanager的相关请求
 * @Author CHENZEJIA
 * @Date 2019/12/17 9:36
 **/
@Controller
@RequestMapping(value = "/userManager")
public class UserManagerController {
    Logger logger = LoggerFactory.getLogger(UserManagerController.class);
    @Autowired
    UserService userService;
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;
    //删除用户的请求
    @RequestMapping(value="/delete/{userId}")
    @ResponseBody
    public Result<Boolean> deleteUser(@PathVariable("userId") int userId){
        logger.info("删除用户");
        boolean result = userService.deleteUser(userId);
        if (result == false) {
            return Result.error(CodeMsg.DELETEUSER_ERROR);
        }else{
            return Result.success(true);
        }
    }
    //前往修改用户信息的页面，从数据库中获取用户的信息
    @RequestMapping(value="/toUpdateUser/{userName}",produces = "text/html")
    @ResponseBody
    public String userManagerUpdateUser(Model model, @PathVariable("userName") String userName,HttpServletRequest request,HttpServletResponse response) {
        User user = userService.selectUserByUserName(userName);
        logger.info(user.toString());
        model.addAttribute("oldUser",user);
        WebContext webContext = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        String html = thymeleafViewResolver.getTemplateEngine().process("user_update",webContext);
        return html;
    }
    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public Result<Boolean> userManagerUpdate(@RequestParam String birthday, @RequestParam int userSex, @RequestParam String telephone, @RequestParam String userName) throws ParseException {
        logger.info("修改用户资料...");
        User user = userService.selectUserByUserName(userName);
        user.setUserSex(userSex);
        user.setTelephone(telephone);
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
        boolean result  = userService.updateUser(user);
        if (result == false) {
            return Result.error(CodeMsg.UPDATEUSER_ERROR);
        }
        SecurityUtils.getSubject().getSession().setAttribute("userSession",user);
        return Result.success(true);
    }
    //用户管理，根据输入的信息，模糊用户名查询相关用户信息
    @RequestMapping(value = "/selectUserByTip/{selectTip}",produces = "text/html")
    @ResponseBody
    public String selectUserBytip(Model model , @PathVariable("selectTip") String selectTip, HttpServletRequest request, HttpServletResponse response) {
        List<UserAndRole> allUsers = userService.selectAllUserByTip(selectTip);
        for (UserAndRole u : allUsers){
            logger.info(u.toString());
        }
        model.addAttribute("allUsers",allUsers);
        WebContext webContext = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        String html = thymeleafViewResolver.getTemplateEngine().process("userManager",webContext);
        return html;
    }
    @RequestMapping(value = "/toAddUser")
    public String toAddUser(Model model,HttpServletRequest request, HttpServletResponse response) {
        return "user_add";
    }
}
