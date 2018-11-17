package com.chenjj.spring.core.model;

import java.io.Serializable;

/**
 * Created by chenjunjiang on 18-11-10.
 */
public class Boss1 implements Serializable {
    private static final long serialVersionUID = -371652551005303952L;

    public Car getCar() {
        Car car = new Car();
        car.setBrand("宝马Z4");

        return car;
    }

}
