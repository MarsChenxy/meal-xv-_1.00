package com.ants.ordermeal.dao;

import com.ants.ordermeal.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wolf
 */
public interface UserDao extends JpaRepository<User,Integer> {

    User findUserByUsernameAndPassword(String username, String md5Password);

    List<User> findUserByUsername(String username);

    List<User> findUserByEmail(String email);
}
