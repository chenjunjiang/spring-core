package com.chenjj.spring.core;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.chenjj.spring.core.model.Car;

import java.io.IOException;

public class BeanFactoryTest {

    @Test
    public void test() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:beans.xml");
        System.out.println(resource.getURL());
        //初始化配置文件的时候实例化bean
        // XmlBeanFactory已经废弃，不建议使用了
        // BeanFactory beanFactory = new XmlBeanFactory(resource);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        System.out.println("init BeanFactory.");
        //初始化bean的动作发生在第一次调用getBean
        Car car = beanFactory.getBean("car1", Car.class);
        System.out.println(car.getBrand() + ":" + car.getColor() + ":" + car.getMaxSpeed());
    }
}
