package com.chenjj.spring.core;

import com.chenjj.spring.core.beanprocessor.AutoAddBeanViaCoding;
import com.chenjj.spring.core.beanprocessor.MyBeanPostProcessor;
import com.chenjj.spring.core.beanprocessor.MyInstantiationAwareBeanPostProcessor;
import com.chenjj.spring.core.model.Dog;
import com.chenjj.spring.core.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
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

    @Test
    public void testLifeCycle() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:beans.xml");
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
        reader.loadBeanDefinitions(resource);

        // 向容器中注册MyBeanPostProcessor后处理器
        ((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyBeanPostProcessor());
        // 向容器中注册MyInstantiationAwareBeanPostProcessor后处理器
        ((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        // 第一次从容器中取Dog，将触发容器实例化该Bean，这将引发Bean生命周期的调用
        Dog dog1 = beanFactory.getBean("dog", Dog.class);
        dog1.introduce();
        dog1.setColor("红色");

        // 第二次从容器中取dog，直接从缓存池中获取，如果Bean的作用范围定义为scope="prototype"，则第二次调用getBean时，
        // 生命周期方法会再次被调用
        Dog dog2 = beanFactory.getBean("dog", Dog.class);

        // 查看dog1和dog2是不是指向同一引用
        System.out.println("dog1==dog2:" + (dog1 == dog2));
        dog2.introduce();

        // 关闭容器
        ((DefaultListableBeanFactory) beanFactory).destroySingletons();
    }

    /**
     * 通过BeanFactory初始化的方式不能让自定义的BeanFactoryPostProcessor生效，下面的测试会报错
     * 要使用ApplicationContext的方式
     */
    @Test
    public void testAutoAddBeanViaCoding() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:beans1.xml");
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
        reader.loadBeanDefinitions(resource);
        UserService userService1 = beanFactory.getBean("userService1", UserService.class);
        System.out.println(userService1.getUser());
    }
}
