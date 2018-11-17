package com.chenjj.spring.core;

import com.chenjj.spring.core.model.Boss;
import com.chenjj.spring.core.model.Car;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chenjunjiang on 18-11-10.
 */
public class ParentSubBeanTest {

    @Test
    public void test1() {
        ClassPathXmlApplicationContext parentContext = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        // 指定父容器为parentContext
        ApplicationContext subContext = new ClassPathXmlApplicationContext(new String[]{"classpath:beans2.xml"}, parentContext);

        Car car = parentContext.getBean("car3", Car.class);
        System.out.println(car);

        Boss boss = subContext.getBean("boss", Boss.class);
        System.out.println(boss.getCar());
    }
}
