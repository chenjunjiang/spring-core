package com.chenjj.spring.core.aop.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态切面
 */
public class GreetingDynamicMethodAdvisor extends DynamicMethodMatcherPointcut {

    private static List<String> specialClientList = new ArrayList<>();

    static {
        specialClientList.add("John");
        specialClientList.add("Tom");
    }

    /**
     * 对方法进行静态检查
     *
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("调用matches(Method method, Class<?> targetClass)" + targetClass.getName() +
                "." + method.getName() + "做静态检查.");
        return "greetTo".equals(method.getName());
    }

    /**
     * 对方法进行动态检查
     * 由于动态切点检查会对性能造成很大的影响，所以应当尽量避免在运行时每次都对目标类的各个方法进行动态检查。Spring采用这样的机制：
     * 在创建代理时对目标类的每个连接点使用静态切点检查，如果仅通过静态切点检查就可以知道连接点是不匹配的，则在运行时就不再进行动态检查；
     * 如果静态切点检查是匹配的，则在运行时才进行动态切点检查。
     *
     * @param method
     * @param targetClass
     * @param args
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("调用matches(Method method, Class<?> targetClass, Object... args)"
                + targetClass.getName() + "." + method.getName() + "做动态检查.");
        String clientName = (String) args[0];
        return specialClientList.contains(clientName);
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> {
            System.out.println("调用getClassFilter()对" + clazz.getName() + "做静态检查.");
            return Caddier.class.isAssignableFrom(clazz);
        };
    }
}
