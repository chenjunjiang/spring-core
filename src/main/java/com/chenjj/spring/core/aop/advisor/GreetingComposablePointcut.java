package com.chenjj.spring.core.aop.advisor;

import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * 复合切面
 */
public class GreetingComposablePointcut {
    public Pointcut getIntersectionPointcut() {
        ComposablePointcut composablePointcut = new ComposablePointcut();
        // 创建一个流程切断
        Pointcut pointcut = new ControlFlowPointcut(WaiterDelegate.class, "service");
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.addMethodName("greetTo");

        return composablePointcut.intersection(pointcut).intersection((MethodMatcher) nameMatchMethodPointcut);
    }
}
