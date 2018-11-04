package com.chenjj.spring.core;

import com.chenjj.spring.core.configuration.Beans;
import com.chenjj.spring.core.model.Car;
import com.chenjj.spring.core.model.Dog;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * ApplicationContext是由BeanFactory派生而来，提供了更多面向实际应用的功能。
 * ApplicationContext初始化应用上下文时就实例化了所有的单实例Bean。
 * ApplicationContext在启动时，就首先为配置文件中的每个<bean/>生成一个BeanDefinition对象，BeanDefinition是<bean/>
 * 在容器中的内部表示。当配置文件中所有<bean/>都被解析成BeanDefinition时，ApplicationContext将调用工厂后处理器
 * (BeanFactoryPostProcessor)的方法。
 * 在配置文件中定义的BeanPostProcessor和BeanFactoryPostProcessor会自动被ApplicationContext识别并注册到容器中。
 *
 * Created by chenjunjiang on 18-10-29.
 */
public class ApplicationContextTest {

    /**
     * ClassPathXmlApplicationContext 默认会去 classPath 路径下找。classPath 路径指的就是编译后的 classes 目录。
     */
    @Test
    public void test1() {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        System.out.println("path = " + path);

        //绝对路径需加“file:”前缀
        // new ClassPathXmlApplicationContext("file:E:\\Workspace\\idea_workspace\\spring\\springtest\\src\\main\\resources\\applicationContext.xml");

        // ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");

        // 这种方式在idea环境下会加载src/test/resources和src/main/resources下的资源
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:*.xml");
        // 这种方式在idea环境下只会加载src/test/resources下的资源，而不会加载src/main/resources下的资源
        // ApplicationContext context = new ClassPathXmlApplicationContext("classpath:*.xml");
        // ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // Car car = context.getBean(Car.class);
        Car car1 = context.getBean("car1", Car.class);
        // System.out.println(car);
        System.out.println(car1);
    }

    /**
     * FileSystemXmlApplicationContext 默认是去项目的路径下加载，可以是相对路径，也可以是绝对路径，若是绝对路径，“file:” 前缀可以缺省。
     */
    @Test
    public void test2() {
        //项目路径相对路径
        // ApplicationContext context = new FileSystemXmlApplicationContext("src/test/resources/beans1.xml");

        //绝对目录
        // new FileSystemXmlApplicationContext(new String[]{"E:\\Workspace\\idea_workspace\\spring\\springtest\\src\\main\\resources\\applicationContext.xml"});

        //classpath,这里就是指classes目录
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:beans1.xml");
        Car car2 = context.getBean("car2", Car.class);
        System.out.println(car2);
    }

    @Test
    public void test3() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
        Car car = context.getBean("car", Car.class);
        System.out.println(car);
    }

    @Test
    public void testLifeCycle() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        Dog dog = context.getBean("dog", Dog.class);
        dog.introduce();
        // Close this application context, destroying all beans in its bean factory
        context.close();
    }
}
