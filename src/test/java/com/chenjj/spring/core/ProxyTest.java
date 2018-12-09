package com.chenjj.spring.core;

import com.chenjj.spring.core.proxy.CglibProxy;
import com.chenjj.spring.core.proxy.ForumService;
import com.chenjj.spring.core.proxy.ForumServiceImpl;
import com.chenjj.spring.core.proxy.PerformanceHandler;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * CGLib所创建的动态代理对象的性能比JDK所创建的动态代理对象的性能高大概10倍左右，但CGLib在创建对象时所花费的时间却比JDK动态代理多大概
 * 8倍左右。对于单例的代理对象或者具有实例池的代理，因为无须频繁地创建代理对象，所有比较适合采用CGLib动态代理技术；反正则使用JDK
 */
public class ProxyTest {
    @Test
    public void jdkProxyTest() {
        ForumService forumService = new ForumServiceImpl();
        PerformanceHandler performanceHandler = new PerformanceHandler(forumService);
        ForumService proxy = (ForumService) Proxy.newProxyInstance(forumService.getClass().getClassLoader(), forumService.getClass().getInterfaces(),
                performanceHandler);
        proxy.removeForum(10);
        proxy.removeTopic(1024);
    }

    @Test
    public void cglibProxyTest() {
        CglibProxy cglibProxy = new CglibProxy();
        ForumServiceImpl forumService = (ForumServiceImpl) cglibProxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(10);
        forumService.removeTopic(1024);
    }

}
