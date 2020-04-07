package com.cmb.demo.controller;

import com.cmb.demo.bean.User;
import com.cmb.demo.utils.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/redisset")
    public boolean redisset(String key) {
        User user = new User();
        user.setName("陶健");
        user.setAge(18);
        user.setAddress("杭州市");

        return redisUtil.set(key, user);
    }

    @RequestMapping("/demo")
    public String demo() {
        return "hello world";
    }

    @RequestMapping("/redisget")
    public Object redisget(String key) {
        return redisUtil.get(key);
    }

}
