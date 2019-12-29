package com.mycompany.a3.objects;
import java.util.Random;

public abstract class GameObject {
	Random rand = new Random();
	private double locationX;
	private double locationY;
	private int color;
	private int speed;
	private int size;
	private int direction; 
	private boolean crashFlag;
			
	public void setLocationX(double x) {
		this.locationX = x;
	}
	
	public double getLocationX() {
		return locationX;
	}
	
	public void setLocationY(double y) {
		this.locationY = y;
	}
	
	public double getLocationY() {
		return locationY;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int randomNumber(int min, int max) {
		int randNum;
		randNum = rand.nextInt(max) + min;
		return randNum;
	}
	
	public double randomNumber(double min, double max) {
		double randNum;
		randNum = min + max * rand.nextDouble();
		return randNum;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isCrashFlag() {
		return crashFlag;
	}

	public void setCrashFlag(boolean crashFlag) {
		this.crashFlag = crashFlag;
	}
	
}
