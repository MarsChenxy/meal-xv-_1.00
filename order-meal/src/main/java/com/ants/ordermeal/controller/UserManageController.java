package com.ants.ordermeal.controller;

import com.ants.ordermeal.common.Const;
import com.ants.ordermeal.common.ServerResponse;
import com.ants.ordermeal.pojo.User;
import com.ants.ordermeal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by wolf
 */

@RestController
@RequestMapping("/manage/user")
public class UserManageController {
    @Autowired
    private  IUserService iUserService;
    //管理员登录
    @PostMapping(value = "/login.do")
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username,password);
        if(response.isSuccess()){
            User user = response.getData();
            if(user.getRole() == Const.Role.ROLE_ADMIN){
                //说明登录的是管理员
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            }else{
                return ServerResponse.createByErrorMessage("不是管理员,无法登录");
            }
        }
        return response;
    }
}
