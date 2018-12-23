package com.chenjj.spring.core.aop.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * 定义静态切面
 */
public class GreetingStaticMethodAdvisor extends StaticMethodMatcherPointcutAdvisor {
    /**
     * 切点方法匹配规则
     * 方法名为greetTo
     *
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "greetTo".equals(method.getName());
    }

    /**
     * 切点类匹配规则
     * Caddier或其子类
     *
     * @return
     */
    @Override
    public ClassFilter getClassFilter() {
        return clazz -> Caddier.class.isAssignableFrom(clazz);
    }
}
