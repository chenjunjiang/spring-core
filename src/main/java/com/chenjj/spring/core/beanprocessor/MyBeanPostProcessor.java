package com.chenjj.spring.core.beanprocessor;

import com.chenjj.spring.core.model.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

/**
 * Created by chenjunjiang on 18-11-4.
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     * @Nullable 表示返回值可以为null
     */
    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 仅对容器中的Dog Bean处理
        if ("dog".equals(beanName)) {
            Dog dog = (Dog) bean;
            if (dog.getColor() == null) {
                System.out.println("调用BeanPostProcessor.postProcessBeforeInitialization(), color为空，设置为黄色");
                dog.setColor("黄色");
            }
        }
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 仅对容器中的Dog Bean处理
        if ("dog".equals(beanName)) {
            Dog dog = (Dog) bean;
            if (dog.getWeight() >= 10) {
                System.out.println("调用BeanPostProcessor.postProcessAfterInitialization(), 将weight调整为10");
                dog.setWeight(10);
            }
        }
        return bean;
    }
}
