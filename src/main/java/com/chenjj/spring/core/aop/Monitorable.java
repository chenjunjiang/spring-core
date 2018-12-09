package com.chenjj.spring.core.aop;

/**
 * 用于标识目标类是否支持性能监视
 */
public interface Monitorable {
    void setMonitorActive(boolean active);
}
