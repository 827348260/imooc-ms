package com.chzero.imooc.ms.controller;

import com.chzero.imooc.ms.redis.RedisService;
import com.chzero.imooc.ms.redis.UserKey;
import com.chzero.imooc.ms.result.Result;
import com.chzero.imooc.ms.service.UserService;
import com.chzero.imooc.ms.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-11 13:51
 * @email 827348260@qq.com
 * @description
 */
@Controller
@RequestMapping(value = "demo")
public class SampleController{

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name", "chzero");
        model.addAttribute("age", "OK");
        model.addAttribute("haha", "OJBK");
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "db/get")
    public Result<User> dbGet(){
        User user = this.userService.getById("1");
        return Result.success(user);
    }

    @ResponseBody
    @RequestMapping(value = "db/tx")
    public Result<Integer> dbTx(){
        User user = new User();
        user.setId("6");
        user.setName("123");
        int i = this.userService.addUser(user);
        user.setId("4");
        user.setName("4");
        int j = this.userService.addUser(user);
        return Result.success(i);
    }


    @ResponseBody
    @RequestMapping(value = "redis/get")
    public Result<String> rediGet(){
        String key1 = this.redisService.get(UserKey.getById, "key1", String.class);
        return Result.success(key1);
    }

    @ResponseBody
    @RequestMapping(value = "redis/set")
    public Result<User> rediSet(){
        User user = new User();
        user.setId("wsID");
        user.setName("1111");
        boolean set = this.redisService.set(UserKey.getById, "user", user);
        User resultUser = this.redisService.get(UserKey.getById, "user", User.class);
        return Result.success(resultUser);
    }
}
