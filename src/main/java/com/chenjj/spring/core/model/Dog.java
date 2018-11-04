package com.chenjj.spring.core.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 用于测试Bean的生命周期
 * Created by chenjunjiang on 18-11-3.
 */
public class Dog implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String name;
    private String color;
    private int weight;

    private BeanFactory beanFactory;
    private String beanName;

    public Dog() {
        System.out.println("调用Dog()构造函数");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("调用setName()设置属性:" + name);
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        System.out.println("调用setColor()设置属性:" + color);
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        System.out.println("调用setWeight()设置属性:" + weight);
        this.weight = weight;
    }

    public void introduce() {
        System.out.println("name:" + name + ";color:" + color + ";weight:" + weight);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用BeanFactoryAware.setBeanFactory():" + beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("调用BeanNameAware.setBeanName():" + name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用DisposableBean.destroy()");
    }

    /**
     * 通过<bean>的init-method属性指定的初始化方法，和实现InitializingBean接口效果一样
     */
    public void myInit() {
        System.out.println("调用init-method所指定的myInit()，将weight设置为10");
        this.weight = 10;
    }

    /**
     * 通过<bean>的destroy-method属性指定的初始化方法，和实现DisposableBean接口效果一样
     */
    public void myDestroy() {
        System.out.println("调用destroy-method所指定的myDestroy()");
    }
}
