package com.heyou.springboot.dao;

import com.heyou.springboot.entity.User;

import java.util.List;

/**
 *
 * @author heyou
 * @date 2019/6/14 0014
 */
public interface UserDao {

    List<User> userList();
}
