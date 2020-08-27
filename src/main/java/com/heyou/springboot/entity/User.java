package com.heyou.springboot.entity;

import com.heyou.springboot.validation.Mobile;
import lombok.Data;

/**
 *
 * @author heyou
 * @date 2019/6/14 0014
 */
@Data
public class User {
    private int userId;
    private String username;
    private String password;
    @Mobile
    private String mobile;

    public User() {
    }

    public User(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

}
