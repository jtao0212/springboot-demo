package com.cmb.demo.controller;

import com.cmb.demo.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping("/getUser")
    public List<User> getUser(){
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("小张");
        user1.setAge(25);
        user1.setAddress("杭州市滨江区");
        User user2 = new User();
        user2.setName("小赵");
        user2.setAge(22);
        user2.setAddress("杭州市滨江区");
        users.add(user1);
        users.add(user2);
        return users;
    }
}
