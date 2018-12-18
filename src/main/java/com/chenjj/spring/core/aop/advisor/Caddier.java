package com.chenjj.spring.core.aop.advisor;

public class Caddier {
    public void greetTo(String name) {
        System.out.println("caddier greet to " + name + "...");
    }

    public void serverTo(String name) {
        System.out.println("caddier serving " + name + "...");
    }
}
