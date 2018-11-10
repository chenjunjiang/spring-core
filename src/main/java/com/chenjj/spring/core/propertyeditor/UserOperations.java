package com.chenjj.spring.core.propertyeditor;

import com.chenjj.spring.core.model.User;

public class UserOperations {

    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setAge(22);
        user.setName("zhangsan");
        return user;
    }
}
