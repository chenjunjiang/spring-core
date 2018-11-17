package com.chenjj.spring.core.model;

import java.io.Serializable;

/**
 * Created by chenjunjiang on 18-11-10.
 */
public class Boss implements Serializable {
    private static final long serialVersionUID = -371652551005303952L;

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
