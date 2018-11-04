package com.chenjj.spring.core.beanprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * Created by chenjunjiang on 18-11-4.
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    /**
     * 在实例化Bean之前调用
     * Apply this BeanPostProcessor to the given new bean instance before any bean initialization callbacks
     * (like InitializingBean's afterPropertiesSet or a custom init-method).
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 仅对容器中的Dog Bean处理
        if ("dog".equals(beanName)) {
            System.out.println("InstantiationAwareBeanPostProcessor.postProcessBeforeInitialization");
        }
        return null;
    }

    /**
     * 在实例化Bean后调用
     * Apply this BeanPostProcessor to the given new bean instance after any bean initialization callbacks
     * (like InitializingBean's afterPropertiesSet or a custom init-method).
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 仅对容器中的Dog Bean处理
        if ("dog".equals(beanName)) {
            System.out.println("InstantiationAwareBeanPostProcessor.postProcessAfterInitialization");
        }
        return null;
    }

    /**
     * 调用bean所有setXX方法前调用
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 仅对容器中的Dog Bean处理
        if ("dog".equals(beanName)) {
            System.out.println("InstantiationAwareBeanPostProcessor.postProcessProperties:" + pvs);
        }
        return pvs;
    }
}
