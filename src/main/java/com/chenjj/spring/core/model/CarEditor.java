package com.chenjj.spring.core.model;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * 按照JavaBean的规范，JavaBean的基础设施会在JavaBean的相同的类包下查找是否存在<JavaBean>Editor的规范类。如果存在，
 * 则自动使用<JavaBean>Editor作为该JavaBean的PropertyEditor。
 * spring也支持这个规范，如果采用这种规则，就无需显示在CustomEditorConfigurer中注册了，spring将自动查找并注册这个PropertyEditor。
 */
public class CarEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            throw new IllegalArgumentException("设置的字符串格式不正确");
        }
        String[] infos = text.split(",");
        Car car = new Car();
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.parseInt(infos[1]));
        car.setColor(infos[2]);

        // 调用父类的setValue()方法设置转换后的属性对象
        setValue(car);
    }
}
