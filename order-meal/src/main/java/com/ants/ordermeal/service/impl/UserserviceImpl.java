package com.ants.ordermeal.service.impl;

import com.ants.ordermeal.common.Const;
import com.ants.ordermeal.common.ServerResponse;
import com.ants.ordermeal.dao.UserDao;
import com.ants.ordermeal.pojo.User;
import com.ants.ordermeal.service.IUserService;
import com.ants.ordermeal.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;

/**
 * Created by wolf
 */
@Service
public class UserserviceImpl implements IUserService{

    private final static Logger logger =  LoggerFactory.getLogger(UserserviceImpl.class);
    @Autowired
    private  UserDao userDao;
    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userDao.findUserByUsername( username ).size();
        if(resultCount == 0 ){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user  =  userDao.findUserByUsernameAndPassword(username,md5Password);
        if(user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);
    }

    @Override
    @Transactional
    public ServerResponse<String> register(User user) {
        ServerResponse validResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setCreateTime(new Date());
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        userDao.save(user);
        logger.info("info={}",user.toString());
        int resultCount=userDao.findUserByUsername(user.getUsername()).size();
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if(org.apache.commons.lang3.StringUtils.isNotBlank(type)){
            //开始校验
            if(Const.USERNAME.equals(type)){
                int resultCount = userDao.findUserByUsername(str).size();
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resultCount = userDao.findUserByEmail(str).size();
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse<User> updateInformation(User user) {
        userDao.save(user);
        User u= userDao.findUserByUsername(user.getUsername()).get(0);
        if(u!=null){
            return   ServerResponse.createBySuccess(u);
        }
        return ServerResponse.createBySuccessMessage("更新未成功！");
    }

    @Override
    public ServerResponse<User> getInformation(Integer userId) {
        return null;
    }

    @Override
    public ServerResponse checkAdminRole(User user) {
        return null;
    }

    @Override
    public ServerResponse selectQuestion(String username) {
        ServerResponse validResponse = this.checkValid(username,Const.USERNAME);

        return null;
    }

    @Override
    public ServerResponse<User> deleteUser(String username) {
        User user = userDao.findUserByUsername(username).get(0);
        if(user!=null){
        userDao.delete(user);
        return ServerResponse.createBySuccess();
        }else{
            return ServerResponse.createByErrorMessage("删除失败，无此用户！");
        }
    }
}
