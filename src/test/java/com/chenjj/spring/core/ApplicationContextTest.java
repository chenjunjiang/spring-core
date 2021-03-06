package com.chenjj.spring.core;

import com.chenjj.spring.core.configuration.Beans;
import com.chenjj.spring.core.event.MailSender;
import com.chenjj.spring.core.factorybean.CarFactoryBean;
import com.chenjj.spring.core.model.Boss;
import com.chenjj.spring.core.model.Boss1;
import com.chenjj.spring.core.model.Car;
import com.chenjj.spring.core.model.Dog;
import com.chenjj.spring.core.model.MagicBoss;
import com.chenjj.spring.core.placeholder.MyDataSource;
import com.chenjj.spring.core.placeholder.SysConfig;
import com.chenjj.spring.core.scope.MyScope;
import com.chenjj.spring.core.service.UserService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.sql.DataSource;

/**
 * ApplicationContext是由BeanFactory派生而来，提供了更多面向实际应用的功能。
 * ApplicationContext初始化应用上下文时就实例化了所有的单实例Bean。
 * ApplicationContext在启动时，就首先为配置文件中的每个<bean/>生成一个BeanDefinition对象，BeanDefinition是<bean/>
 * 在容器中的内部表示。当配置文件中所有<bean/>都被解析成BeanDefinition时，ApplicationContext将调用工厂后处理器
 * (BeanFactoryPostProcessor)的方法。
 * 在配置文件中定义的BeanPostProcessor和BeanFactoryPostProcessor会自动被ApplicationContext识别并注册到容器中。
 * <p>
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

        // 代码写在src/test/java下的时候，这种方式在idea环境下会同时加载src/test/resources和src/main/resources下的资源
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:beans1.xml");

        // 代码写在src/test/java下的时候，以下两种方式在idea环境下会优先加载src/test/resources下的资源，
        // 如果找不到指定文件，再加载src/main/resources下的文件
        // ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        // ApplicationContext context = new ClassPathXmlApplicationContext("beans1.xml");
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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
        Car car = context.getBean("car", Car.class);
        System.out.println(car);
        context.close();
    }

    @Test
    public void testLifeCycle() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        Dog dog = context.getBean("dog", Dog.class);
        dog.introduce();
        // Close this application context, destroying all beans in its bean factory
        context.close();
    }

    @Test
    public void testLookupMethod() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        Boss boss = context.getBean("boss", Boss.class);
        Car car1 = boss.getCar();
        Car car2 = boss.getCar();
        System.out.println(car1 == car2);// true

        MagicBoss magicBoss = context.getBean("magicBoss", MagicBoss.class);
        Car car3 = magicBoss.getCar();
        Car car4 = magicBoss.getCar();
        System.out.println(car3 == car4);// false
    }

    @Test
    public void testReplaceMethod() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        Boss1 boss1 = context.getBean("boss1", Boss1.class);
        System.out.println(boss1.getCar());
    }

    @Test
    public void testCustomScope() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        context.getBeanFactory().registerScope("myScope", new MyScope());
        Car car = context.getBean("car", Car.class);
        Car car1 = context.getBean("car", Car.class);
        System.out.println(car.toString());
        System.out.println(car1.toString());
        System.out.println(car == car1);
    }

    @Test
    public void testFactoryBean() {
        // Spring通过反射机制发现CarFactoryBean实现了FactoryBean接口，当调用getBean时，spring容器就调用接口方法CarFactoryBean
        // &getObject返回工厂类创建的对象。如果希望获取CarFactoryBean的实例，则需要加上&前缀
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        Car car = context.getBean("car2", Car.class);
        CarFactoryBean carFactoryBean = context.getBean("&car2", CarFactoryBean.class);
        System.out.println(car);
        System.out.println(carFactoryBean);
    }

    @Test
    public void testAutoAddBeanViaCoding() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        UserService userService1 = context.getBean("userService1", UserService.class);
        UserService userService2 = context.getBean("userService2", UserService.class);
        System.out.println(userService1.getUser());
        System.out.println(userService2.getUser());
    }

    @Test
    public void testCustomLabel() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        UserService userService3 = context.getBean("userService3", UserService.class);
        System.out.println(userService3.getUser());
    }

    @Test
    public void testCustomPropertyEditor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        Boss boss = context.getBean("boss4", Boss.class);
        System.out.println(boss.getCar());
    }

    @Test
    public void testCustomPropertyEditor2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans1.xml");
        Boss boss = context.getBean("boss5", Boss.class);
        System.out.println(boss.getCar());
    }

    @Test
    public void testPropertyPlaceholderConfigurer() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:datasource.xml");
        DataSource dataSource = context.getBean("dataSource", BasicDataSource.class);
        SysConfig sysConfig = context.getBean(SysConfig.class);
        MyDataSource myDataSource = context.getBean(MyDataSource.class);
        System.out.println(dataSource);
        System.out.println(myDataSource);
        System.out.println(sysConfig);
    }

    /**
     * Spring在ApplicationContext接口的抽象实现类AbstractApplicationContext中完成了事件体系的搭建。AbstractApplicationContext拥有
     * 一个applicationEventMulticaster成员变量，applicationEventMulticaster提供了容器监听器的注册表。AbstractApplicationContext
     * 在refresh()这个容器启动方法中通过以下3个步骤搭建了事件的基础设施：
     * 初始化事件的广播器，用户可以在配置文件中为容器定义一个自定义的事件广播器，只要实现ApplicationEventMulticaster即可，spring会通过
     * 反射机制将其注册为容器的广播器。如果没有找到配置的外部事件广播器，则spring自动使用SimpleApplicationEventMulticaster作为事件广播器
     * initApplicationEventMulticaster();
     * spring根据反射机制，从BeanDefinitionRegistry中找出实现ApplicationListener的Bean，将它们注册为容器的事件监听器，实际操作就是将
     * 其添加到事件广播器所提供的事件监听器注册表中。
     * registerListeners();
     * 容器启动完成，调用事件发布接口向容器中所有的监听器发布事件。在publishEvent()内部可以看到，spring委托applicationEventMulticaster
     * 将事件通知给事件监听器
     * finishRefresh();
     */
    @Test
    public void testEvent() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:event.xml");
        MailSender mailSender = context.getBean("mainSender", MailSender.class);
        mailSender.sendMail("chenjj@yeah.net");
    }
}
