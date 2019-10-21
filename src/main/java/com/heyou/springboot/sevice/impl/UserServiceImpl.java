package com.heyou.springboot.sevice.impl;

import com.heyou.springboot.dao.UserDao;
import com.heyou.springboot.entity.User;
import com.heyou.springboot.sevice.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by heyou on 2019/6/14 0014.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.login(user);
    }
}
