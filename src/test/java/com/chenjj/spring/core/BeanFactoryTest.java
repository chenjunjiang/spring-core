package com.chenjj.spring.core;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.chenjj.spring.model.Car;

public class BeanFactoryTest {

	@Test
	public void test(){
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource("classpath:beans.xml");
		//初始化配置文件的时候实例化bean
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		//此时才会实例化bean
		Car car = beanFactory.getBean("car1", Car.class);
		System.out.println(car.getBrand()+":"+car.getColor()+":"+car.getMaxSpeed());
	}
}
