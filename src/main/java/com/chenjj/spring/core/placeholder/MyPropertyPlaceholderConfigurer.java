package com.chenjj.spring.core.placeholder;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if ("password".equals(propertyName)){
            propertyValue = "root";
        }

        return propertyValue;
    }
}
