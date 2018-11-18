package com.chenjj.spring.core.service;

import com.chenjj.spring.core.dao.UserDao;
import com.chenjj.spring.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser() {
        return userDao.getUser();
    }
}
