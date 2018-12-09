package com.chenjj.spring.core.aop;

import java.sql.SQLException;

public class ExceptionService {
    public void removeForum() {
        throw new RuntimeException("运行异常");
    }

    public void updateForum() throws SQLException {
        throw new SQLException("数据库更新操作异常");
    }
}
