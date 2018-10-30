package com.chenjj.spring.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 资源加载
 * 更对关于资源加载的只是请参考有道云笔记里面的spring resource小节
 * Created by chenjunjiang on 18-10-28.
 */
public class ResourceTest {

    @Test
    public void getResources() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 加载类路径下所有类包com.chenjj（及其子包）下以.xml为后缀的资源
        // Resource[] resources = resolver.getResources("classpath*:com/chenjj/**/*.xml");
        // Resource[] resources = resolver.getResources("classpath*:beans.xml");
        // Resource[] resources = resolver.getResources("classpath:beans.xml");
        // Resource[] resources = resolver.getResources("beans.xml");
        // Resource[] resources = resolver.getResources("*.xml");
        Resource[] resources = resolver.getResources("classpath:*.xml");
        Assert.assertNotNull(resources);
        Assert.assertEquals(true, resources.length > 0);
        /*for (Resource resource : resources) {
            System.out.println(resource.getDescription());
        }*/
        Arrays.asList(resources).forEach(resource -> System.out.println(resource.getDescription()));
        System.out.println("===================================");
        URL url = this.getClass().getClassLoader().getResource("beans.xml");
        System.out.println(url.toString());
        System.out.println("-----------------------------------");
        Enumeration<URL> enumeration = this.getClass().getClassLoader().getResources("beans.xml");
        while (enumeration.hasMoreElements()) {
            URL url1 = enumeration.nextElement();
            System.out.println(url1.toString());
        }
    }
}
