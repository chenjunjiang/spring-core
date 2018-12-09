package com.chenjj.spring.core.aop;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * 方法名必须为afterThrowing，方法入参规定如下：
 * 前３个入参Method method, Object[] args, Object target
 * 是可选的（３个要么提供，要么不提供），而最后一个入参是Throwable或其子类，如以下方法是合法的：
 * afterThrowing(SQLException e)
 * afterThrowing(RuntimeException e)
 * afterThrowing(Method method, Object[] args, Object target,RuntimeException e)
 * 而以下方法是非法的：
 * afterThrowing(Object[] args, Object target,RuntimeException e),缺少Method
 * solveThrowing(SQLException e),方法名非法
 * 可以在同一个异常抛出增强中定义多个afterThrowing方法，当目标类方法抛出异常时，Spring会自动选择最匹配的增强方法。
 */
public class ExceptionManager implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, Exception e) throws Throwable {
        System.out.println("---------------");
        System.out.println("method:" + method.getName());
        System.out.println("抛出异常:" + e.getMessage());
        System.out.println("成功回滚事务");
    }
}
