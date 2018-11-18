package com.chenjj.spring.core.beanprocessor;

import com.chenjj.spring.core.dao.UserDao;
import com.chenjj.spring.core.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class AutoAddBeanViaCoding implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        // 创建Bean定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserService.class);
        beanDefinitionBuilder.addPropertyReference("userDao", "userDao");
        // 注册Bean
        // defaultListableBeanFactory.registerBeanDefinition("userService1", beanDefinitionBuilder.getRawBeanDefinition());
        defaultListableBeanFactory.registerBeanDefinition("userService1", beanDefinitionBuilder.getBeanDefinition());
        // 直接注册一个Bean
        UserDao userDao = beanFactory.getBean("userDao", UserDao.class);
        UserService userService = new UserService();
        userService.setUserDao(userDao);
        defaultListableBeanFactory.registerSingleton("userService2", userService);
    }
}
