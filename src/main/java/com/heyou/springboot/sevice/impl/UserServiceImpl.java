package com.heyou.springboot.sevice.impl;

import com.heyou.springboot.dao.IUserDao;
import com.heyou.springboot.entity.User;
import com.heyou.springboot.sevice.UserService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
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
    private IUserDao userDao;

    @Override
    @Retryable(value = Exception.class,maxAttempts = 3,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    public List<User> getUserList() throws Exception{
        return userDao.userList();
    }
}
