package com.chenjj.spring.core;

import java.io.File;

public class TestFile {
    public static void main(String[] args) {
        File file = new File("/home/chenjunjiang/workspace");
        System.out.println(file.getTotalSpace());
        System.out.println(file.getFreeSpace());
        System.out.println((file.getTotalSpace() - file.getFreeSpace()) / (double) file.getTotalSpace());

        String string = "xxx";
        String result = testString(string);
        System.out.println(result);
        System.out.println(string);
    }

    public static String testString(String string) {
        string = "123";
        return string;
    }
}
