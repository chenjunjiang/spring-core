package com.chenjj.spring.core;

import com.chenjj.spring.core.model.User;
import com.chenjj.spring.core.propertyeditor.UserOperations;
import com.chenjj.spring.core.propertyeditor.UserOperationsEditor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by chenjunjiang on 18-11-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class PropertyEditorTest {

    /**
     * myTemplate和UserOperations没有任何关系，不是同一种类型，也没有继承关系，那是怎么注册成功的呢？
     *
     * @Resource 的机制不仅仅是通过类型和名称注册，还可以通过属性编辑器来注册，具体查看{@link UserOperationsEditor}
     * 示例。其实，上面的HashOperations的注入就是通过这种方式把redisTemplate注入进去的
     */
    @Resource(name = "myTemplate")
    private UserOperations userOperations;

    @Test
    public void testUserOperations() {
        User user = userOperations.getUser();
        System.out.println(user);
    }
}
