package com.chenjj.spring.core.aop;

import com.chenjj.spring.core.proxy.PerformanceMonitor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class ControllablePerformanceMonitor extends DelegatingIntroductionInterceptor implements Monitorable {
    private ThreadLocal<Boolean> monitorStatusMap = new ThreadLocal<>();

    @Override
    public void setMonitorActive(boolean active) {
        monitorStatusMap.set(active);
    }

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object object = null;
        if (monitorStatusMap.get() != null && monitorStatusMap.get()) {
            PerformanceMonitor.begin(methodInvocation.getClass().getName() + "." + methodInvocation
                    .getMethod().getName());
            object = super.invoke(methodInvocation);
            PerformanceMonitor.end();
        } else {
            object = super.invoke(methodInvocation);
        }

        return object;
    }
}
