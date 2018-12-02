package com.chenjj.spring.core;

import com.chenjj.spring.core.proxy.CglibProxy;
import com.chenjj.spring.core.proxy.ForumService;
import com.chenjj.spring.core.proxy.ForumServiceImpl;
import com.chenjj.spring.core.proxy.PerformanceHandler;
import org.junit.Test;

import java.lang.reflect.Proxy;

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
