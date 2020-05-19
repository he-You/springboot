package com.heyou.springboot.sevice;

import com.heyou.springboot.entity.User;

import java.util.List;

/**
 * Created by heyou on 2019/6/14 0014.
 */

public interface UserService {
    List<User> getUserList() throws Exception;
}
