package com.chenjj.spring.core.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MailSender implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void sendMail(String to){
        System.out.println("MailSender模拟发送邮件...");
        MailSendEvent mailSendEvent = new MailSendEvent(this.applicationContext, to);
        // 想容器中的所有事件监听器发送事件
        applicationContext.publishEvent(mailSendEvent);
    }
}
