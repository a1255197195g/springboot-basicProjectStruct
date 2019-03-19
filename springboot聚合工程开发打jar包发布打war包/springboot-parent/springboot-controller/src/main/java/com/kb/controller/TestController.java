package com.kb.controller;

import com.kb.entity.UserEntity;
import com.kb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private UserService userService;


    @RequestMapping("/test")
    @ResponseBody
    public String test(){

        UserEntity userEntity =  userService.getUserInfo();
        return userEntity.getId() +" : " + userEntity.getName();
    }
}
