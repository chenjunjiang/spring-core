package com.chenjj.spring.core.propertyeditor;

import com.chenjj.spring.core.service.MyOperations;

import java.beans.PropertyEditorSupport;

/**
 * 这里的UserOperationsEditor和接口UserOperations必须在同一个包下，因为spring处理的时候会 使用反射，源码可以参考{@link
 * org.springframework.beans.BeanUtils}的findEditorByConvention 方法：String editorName =
 * targetType.getName() + "Editor";
 * UserOperationsEditor必须是PropertyEditor的实现类，我们一般继承PropertyEditorSupport即可。
 * 在setValue方法里面我们就可以把目标对象（myTemplate）转换成我们需要的对象(userOperations)即可， 最后由spring完成bean注入。详细过程断点源码就清楚了。
 */
public class UserOperationsEditor extends PropertyEditorSupport {

    @Override
    public void setValue(Object value) {
        if (value instanceof MyOperations) {
            super.setValue(((MyOperations) value).opsForUser());
        } else {
            throw new IllegalArgumentException(
                    "Editor supports only conversion of type " + MyOperations.class);
        }
    }
}
