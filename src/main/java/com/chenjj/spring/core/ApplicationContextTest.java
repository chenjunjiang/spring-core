package com.chenjj.spring.core;

import com.chenjj.spring.core.model.Boss;
import com.chenjj.spring.core.model.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chenjunjiang on 18-11-10.
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        // 代码写在src/main/java下的时候，如果是通过ClassPathXmlApplicationContext来加载文件，
        // 不管configLocation是classpath:beans1.xml或者classpath*:beans1.xml，还是beans1.xml，
        // 都不会加载src/test/resources下的文件
        ClassPathXmlApplicationContext parentContext = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        ApplicationContext subContext = new ClassPathXmlApplicationContext(new String[]{"classpath:beans2.xml"}, parentContext);

        Car car = parentContext.getBean("car3", Car.class);
        System.out.println(car);

        Boss boss = subContext.getBean("boss", Boss.class);
        System.out.println(boss.getCar());
    }
}
