package com.chenjj.spring.core.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    /**
     * 在目标类方法调用前执行
     *
     * @param method
     * @param args
     * @param object
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object object) throws Throwable {
        String clientName = (String) args[0];
        System.out.println("How are you! Mr." + clientName + ".");
    }
}
