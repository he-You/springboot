package com.heyou.springboot.dao;

import com.heyou.springboot.entity.User;

/**
 * Created by heyou on 2019/6/14 0014.
 */
public interface UserDao {
    User login(User user);
}
