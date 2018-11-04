package com.chenjj.spring.core.beanprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by chenjunjiang on 18-11-4.
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * Modify the application context's internal bean factory after its standard initialization.
     * All bean definitions will have been loaded, but no beans will have been instantiated yet.
     * This allows for overriding or adding properties even to eager-initializing beans.
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("dog");
        // 对dog <bean>的name属性配置信息进行"偷梁换柱"的加工操作
        beanDefinition.getPropertyValues().addPropertyValue("name", "冬瓜");
        System.out.println("调用BeanFactoryPostProcessor.postProcessBeanFactory()");
    }
}
