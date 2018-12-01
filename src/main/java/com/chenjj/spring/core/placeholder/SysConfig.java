package com.chenjj.spring.core.placeholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SysConfig {

    @Value("${sessionTimeout}")
    private int sessionTimeout = 2000;

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    @Override
    public String toString() {
        return "SysConfig{" +
                "sessionTimeout=" + sessionTimeout +
                '}';
    }
}
