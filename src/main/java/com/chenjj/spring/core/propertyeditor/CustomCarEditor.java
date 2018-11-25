package com.chenjj.spring.core.propertyeditor;

import com.chenjj.spring.core.model.Car;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * 由于并不需要将Boss内部的car属性回显到属性编辑器的UI界面中，因此不需要覆盖getAsText()方法
 * 关于JavaBean属性编辑器的更多知识，请参考：
 * https://blog.csdn.net/shenchaohao12321/article/details/80295371
 */
public class CustomCarEditor extends PropertyEditorSupport {
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
