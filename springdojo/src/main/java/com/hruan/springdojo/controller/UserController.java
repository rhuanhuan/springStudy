package com.hruan.springdojo.controller;

import com.hruan.springdojo.MyAutoWried;
import com.hruan.springdojo.service.UserService;

public class UserController {
    @MyAutoWried
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
