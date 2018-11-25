package com.chenjj.spring.core.parser;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 将UserServiceDefinitionParser注册到Spring命名空间解析器
 */
public class UserServiceNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user-service", new UserServiceDefinitionParser());
    }
}
