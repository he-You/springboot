package com.heyou.springboot.entity;

import lombok.Data;

/**
 *
 * @author heyou
 * @date 2019/6/14 0014
 */
@Data
public class User {
    private Integer userId;
    private String username;
    private String password;
    public User(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

}
