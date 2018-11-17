package com.chenjj.spring.core.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by chenjunjiang on 18-11-11.
 */
public class MagicBossImpl implements MagicBoss, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public Car getCar() {
        return (Car) applicationContext.getBean("car1");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
