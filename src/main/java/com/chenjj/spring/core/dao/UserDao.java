package com.chenjj.spring.core.dao;

import com.chenjj.spring.core.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setName("zhangsan");
        user.setAge(20);

        return user;
    }
}
