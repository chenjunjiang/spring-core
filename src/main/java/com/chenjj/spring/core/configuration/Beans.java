package com.chenjj.spring.core.configuration;

import com.chenjj.spring.core.model.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenjunjiang on 18-10-30.
 */
@Configuration
public class Beans {

    @Bean(name = "car")
    public Car car() {
        Car car = new Car();
        car.setBrand("捷豹");
        car.setColor("黑色");
        car.setMaxSpeed(180);

        return car;
    }
}
