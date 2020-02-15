package com.heyou.springboot.sevice.impl;

import com.heyou.springboot.dao.UserDao;
import com.heyou.springboot.entity.User;
import com.heyou.springboot.sevice.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author heyou
 * @date 2019/6/14 0014
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public List<User> getUserList() {
        return userDao.userList();
    }
}
