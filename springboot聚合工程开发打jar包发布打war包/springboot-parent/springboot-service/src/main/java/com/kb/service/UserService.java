package com.kb.service;


import com.kb.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserEntity getUserInfo(){

        UserEntity userEntity = new UserEntity();
        userEntity.setName("mengze");
        userEntity.setId(1);
        return userEntity;
    }

}
