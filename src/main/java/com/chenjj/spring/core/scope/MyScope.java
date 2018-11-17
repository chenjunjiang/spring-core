package com.chenjj.spring.core.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义Bean作用域范围
 */
public class MyScope implements Scope {

    private Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    /**
     * 获取指定beanName的bean实例
     * 每从bean容器中获取一次beanName的实例，对应MyScope的get()方法就会被调用一次
     *
     * @param name          对应bean的beanName
     * @param objectFactory 可以产生对应bean实例的ObjectFactory
     * @return 获取到的实例
     */
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("------------get-----------" + name);
        synchronized (this) {
            if (!beanMap.containsKey(name)) {
                System.out.println("-----------not--exists-------" + name);
                beanMap.put(name, objectFactory.getObject());
            }
        }
        return beanMap.get(name);
    }

    /**
     * 底层移除name对应的对象。实现者需要同时移除注册的销毁化回调方法
     *
     * @param name
     * @return 移除的对象
     */
    public Object remove(String name) {
        System.out.println("remove " + name);
        return beanMap.remove(name);
    }

    /**
     * 注册一个销毁时的回调方法
     *
     * @param name
     * @param callback
     */
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    public Object resolveContextualObject(String key) {
        return null;
    }

    public String getConversationId() {
        return null;
    }

}
