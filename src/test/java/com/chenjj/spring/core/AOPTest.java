package com.chenjj.spring.core;

import com.chenjj.spring.core.aop.*;
import com.chenjj.spring.core.proxy.ForumService;
import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class AOPTest {
    /**
     * 具体会使用哪种方式代理可以跟源码DefaultAopProxyFactory查看
     * <p>
     * public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
     * if (config.isOptimize() || config.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(config)) {
     * Class<?> targetClass = config.getTargetClass();
     * if (targetClass == null) {
     * throw new AopConfigException("TargetSource cannot determine target class: " +
     * "Either an interface or a target is required for proxy creation.");
     * }
     * if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
     * return new JdkDynamicAopProxy(config);
     * }
     * return new ObjenesisCglibAopProxy(config);
     * }
     * else {
     * return new JdkDynamicAopProxy(config);
     * }
     * }
     */
    @Test
    public void testBeforeAdvice() {
        Waiter waiter = new NavieWaiter();
        BeforeAdvice beforeAdvice = new GreetingBeforeAdvice();
        ProxyFactory proxyFactory = new ProxyFactory();
        // 指定目标对象的接口后会使用JDK动态代理
        // proxyFactory.setInterfaces(waiter.getClass().getInterfaces());
        // 会使用CGLib进行代理
        // proxyFactory.setOptimize(true);
        proxyFactory.setTarget(waiter);
        proxyFactory.addAdvice(beforeAdvice);
        // 生成代理对象
        Waiter proxy = (Waiter) proxyFactory.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");
    }

    @Test
    public void testAdviceWithXml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop.xml");
        Waiter waiter = (Waiter) context.getBean("waiter");
        waiter.greetTo("zhangsan");
        waiter.serveTo("lisi");
    }

    @Test
    public void testExceptionAdvice() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop.xml");
        ExceptionService exceptionService = (ExceptionService) context.getBean("exceptionService");
        try {
            exceptionService.removeForum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        exceptionService.updateForum();
    }

    /**
     * 测试引介增强
     */
    @Test
    public void testIntroduce() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop.xml");
        ForumService forumService = (ForumService) context.getBean("forumService");
        // 默认情况下没有开启性能监控
        forumService.removeForum(10);
        forumService.removeTopic(1022);

        System.out.println("*********************");

        // 开启性能监控
        Monitorable monitorable = (Monitorable) forumService;
        monitorable.setMonitorActive(true);

        forumService.removeForum(10);
        forumService.removeTopic(1022);
    }
}
