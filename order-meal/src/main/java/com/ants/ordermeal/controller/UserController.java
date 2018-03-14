package com.ants.ordermeal.controller;

import com.ants.ordermeal.common.Const;
import com.ants.ordermeal.common.ServerResponse;
import com.ants.ordermeal.pojo.User;
import com.ants.ordermeal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by wolf
 */
@RestController
@RequestMapping(value="/user")
public class UserController {


    @Autowired
    private IUserService iUserService;
    //普通用户登录
    @PostMapping(value = "/login.do")
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response =iUserService.login(username,password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
    //用户登出
    @GetMapping(value="/logout.do")
    public ServerResponse<User> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }
    //用户注册
    @PostMapping(value="/register.do")
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }
    //获取用户信息
    @PostMapping(value = "/getuserinfo.do")
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user!=null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录 ，无法获取当前用户信息");
    }
    //更新信息
    @PostMapping(value = "/updateuserinfo.do")
    public ServerResponse<User> updateUserInfo(HttpSession session,User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser==null){
            ServerResponse.createByErrorMessage("当前用户未登录");
        }
        user.setUpdateTime(new Date());
        ServerResponse <User> response=iUserService.updateInformation(user);
        if(response.isSuccess()){
            response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @PostMapping(value = "/delete.do")
    public ServerResponse<User> deleteUser(String username){
        ServerResponse <User> response= iUserService.deleteUser(username);
        return response;
    }

}
