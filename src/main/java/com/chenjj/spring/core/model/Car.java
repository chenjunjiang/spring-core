package com.chenjj.spring.core.model;

import java.io.Serializable;

public class Car implements Serializable{

	private static final long serialVersionUID = -6903170244194658712L;
	
	private String brand;
	private String color;
	private int maxSpeed;
	
	public Car(){
		System.out.println("初始化配置文件的时候不会调用");
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [brand=");
		builder.append(brand);
		builder.append(", color=");
		builder.append(color);
		builder.append(", maxSpeed=");
		builder.append(maxSpeed);
		builder.append("]");
		return builder.toString();
	}
	
}
