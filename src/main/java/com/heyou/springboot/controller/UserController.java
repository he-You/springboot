package com.heyou.springboot.controller;

import com.google.common.base.Objects;
import com.heyou.springboot.constant.Enum.CodeEnum;
import com.heyou.springboot.entity.base.Result;
import com.heyou.springboot.entity.User;
import com.heyou.springboot.entity.requestInfo.ReqUser.ReqLogin;
import com.heyou.springboot.sevice.impl.UserServiceImpl;
import com.heyou.springboot.sevice.redis.RedisService;
import com.heyou.springboot.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author heyou
 * @date 2019/6/14 0014
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/user/*")
public class UserController {
    @Resource
    private UserServiceImpl userService;
    @Resource
    private RedisService redisService;
    @Resource
    private JwtUtil jwtUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static List<User> userList = new ArrayList<>();{
        userList.add(new User(1, "admin", "123456"));
        userList.add(new User(2, "jacks", "111111"));
    }

    @ApiOperation("获取列表")
    @GetMapping("list")
    public Result userList() {
        Result result = new Result();
        result.setData(userService.getUserList());
        result.setSuccess(true);
        result.setMsg("成功");
        return result;
    }

    @ApiOperation("新增用户")
    @PostMapping("save")
    public boolean save(User user) {
        return userList.add(user);
    }

    @ApiOperation("更新用户")
    @ApiImplicitParam(name = "user", value = "单个用户信息", dataType = "User")
    @PutMapping("update")
    public boolean update(User user) {
        return userList.remove(user) && userList.add(user);
    }

    @ApiOperation("批量删除")
    @ApiImplicitParam(name = "users", value = "N个用户信息", dataType = "List<User>")
    @DeleteMapping("delete")
    public boolean delete(@RequestBody List<User> users) {
        return userList.removeAll(users);
    }

    @ApiOperation("登录")
    @ApiImplicitParam(name = "login", value = "单个用户信息", dataType = "User")
    @RequestMapping("login")
    public Result login(@RequestBody ReqLogin userInfo){
        Result result= new Result();
        if(valid(userInfo.getUserName(),userInfo.getPassword())) {
            result.error(CodeEnum.LOGIN_NAMEANDPWD_ERROR.getCode(),CodeEnum.LOGIN_NAMEANDPWD_ERROR.getMsg());
        }
        Map<String,String> map = createUserInfoMap(userInfo.getUserName(),userInfo.getPassword());
        String token = jwtUtil.createToken(map, 1);
        userInfo.setToken(token);
        logger.info(token);
        //存储token
        //redisService.setStr(userInfo.getUserName(),token);
        redisService.set("TOKEN",token);
        logger.info(redisService.get(userInfo.getUserName()));

        result.setSuccess(true);
        result.setMsg("登录成功");
        result.setData(userInfo);
        return result;
    }

    /**
     * 验证用户名密码是否合法
     * @param userName
     * @param password
     * @return
     */
    private boolean valid(String userName, String password) {
        return Objects.equal("heyou", userName) && Objects.equal("123456", password);
    }
    private Map<String,String> createUserInfoMap(String userName, String password) {
        Map<String,String> userInfo = new HashMap<>();
        userInfo.put("loginName", userName);
        userInfo.put("password", password);
        return userInfo;
    }
}

