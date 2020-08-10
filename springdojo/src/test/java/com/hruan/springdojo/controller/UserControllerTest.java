package com.hruan.springdojo.controller;

import com.hruan.springdojo.service.UserService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class UserControllerTest {
    @Test
    void test_autowired_reflect() throws Exception {

        // 获取controller类
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();

        // 创建对象
        UserService userService = new UserService();

        // 获取controller类的属性
        Field serviceField = clazz.getDeclaredField("userService");
//        serviceField.setAccessible(true);

        // 获取字段名
        String serviceFieldName = serviceField.getName();

        // 获取字段的set方法
        String methodName = "set" + serviceFieldName.substring(0, 1).toUpperCase() + serviceFieldName.substring(1, serviceFieldName.length());

        // 反射获取方法并执行
        Method method = clazz.getMethod(methodName, UserService.class);
        method.invoke(userController, userService);

        System.out.println(userController.getUserService());
    }
}