package com.chenjj.spring.core;

import java.io.File;

public class TestFile {
    public static void main(String[] args) {
        File file = new File("/home/chenjunjiang/workspace");
        System.out.println(file.getTotalSpace());
        System.out.println(file.getFreeSpace());
        System.out.println((file.getTotalSpace() - file.getFreeSpace()) / (double) file.getTotalSpace());
    }
}
