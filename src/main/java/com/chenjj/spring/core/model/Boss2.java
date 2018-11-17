package com.chenjj.spring.core.model;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Created by chenjunjiang on 18-11-11.
 */
public class Boss2 implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        Car car = new Car();
        car.setBrand("美人豹");

        return car;
    }
}
