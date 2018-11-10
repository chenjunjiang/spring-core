package com.chenjj.spring.core.service;

import com.chenjj.spring.core.propertyeditor.UserOperations;

public class MyTemplate implements MyOperations {

  @Override
  public UserOperations opsForUser() {
    return new UserOperations();
  }
}
