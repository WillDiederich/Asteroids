package com.mycompany.a3.objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IDrawable;
import com.mycompany.a3.Interfaces.IMovable;
import com.mycompany.a3.Interfaces.ISelectable;


public class Asteroid extends GameObject implements IMovable, IDrawable, ICollider, ISelectable{
	private boolean isSelected;
	public Asteroid() {
		setColor(ColorUtil.BLUE);
		this.setSize(randomNumber(6, 30));
		this.setDirection(randomNumber(0, 359));
		this.setSpeed(randomNumber(1, 4));
		this.setCrashFlag(false);
	}
	
	public Asteroid(int givenSize) {
		setColor(ColorUtil.BLUE);
		setSize(givenSize);
		this.setDirection(randomNumber(0, 359));
		this.setSpeed(randomNumber(1, 4));
	}
	
	public void draw(Graphics g) {
		move();
		g.setColor(this.getColor());	
		g.fillRect((int)this.getLocationX(), (int)this.getLocationY(), this.getSize()+20, this.getSize()+20);
	}
		
	public void move(){
		int delta = 90 - this.getDirection();
		double deltaX = (Math.cos(Math.toRadians(delta))) * this.getSpeed();
		double deltaY = (Math.sin(Math.toRadians(delta))) * this.getSpeed();
		this.setLocationX(Math.round((this.getLocationX() + deltaX)*10.0)/10.0);
		this.setLocationY(Math.round((this.getLocationY() + deltaY)*10.0)/10.0);
	}

	@Override
	public String toString() {
		String asteroidOutput = "";
		asteroidOutput += "Asteroid: " + 
						  "loc=("+ this.getLocationX() + "," + this.getLocationY() + ")" + 
						  ", color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" + 
						  " speed=" + this.getSpeed() +
						  " dir=" + this.getDirection() +
						  " size=" + this.getSize() + 
						  "\n";		
		return asteroidOutput;
	}

	@Override
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		int thisCenterX = (int)this.getLocationX() + ((this.getSize()+20)/2);
		int thisCenterY = (int)this.getLocationY() + ((this.getSize()+20)/2);
		int otherCenterX = (int) (((GameObject)otherObject).getLocationX() + ((GameObject)otherObject).getSize()/2); 
		int otherCenterY = (int) (((GameObject)otherObject).getLocationY() + (((GameObject)otherObject).getSize()/2));
		int distX = thisCenterX - otherCenterX;
		int distY = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (distX*distX + distY*distY);
		int thisRadius = (this.getSize()+20)/2; 
		int otherRadius = ((GameObject)otherObject).getSize()/2; 
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius); 
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true; 
			} 
		return result ; 
	}

	public void handleCollision(ICollider otherObject) {
		if(otherObject instanceof Ship) {
			this.setCrashFlag(true);
			((Ship) otherObject).setCrashFlag(true);
		}
		
		if(otherObject instanceof Asteroid) {
			this.setCrashFlag(true);
			((Asteroid) otherObject).setCrashFlag(true);
		}
		
		if(otherObject instanceof Missile) {
			this.setCrashFlag(true);
			((GameObject) otherObject).setCrashFlag(true);
			
		}
		
		if(otherObject instanceof FlyingSaucer) {
			this.setCrashFlag(true);
			((GameObject) otherObject).setCrashFlag(true);
		}
	}
	
	@Override
	public void setSelectedFalse() {
		this.isSelected = false;
		this.setColor(ColorUtil.BLUE);
	}
	
	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return this.isSelected;
	}

	@Override
	public void setSelected(int pPtrRelPrnt, int pCmpRelPrnt) {
		if(this.getLocationX()-25 <= pPtrRelPrnt && pPtrRelPrnt <= this.getLocationX()+25) {
			if(this.getLocationY()-180 <= pCmpRelPrnt && pCmpRelPrnt <= this.getLocationY()+220) {
				this.isSelected = true;
				this.setColor(ColorUtil.BLACK);
			}
		}
		else {
			this.isSelected = false;
			this.setColor(ColorUtil.BLUE);
		}
	}

	@Override
	public void revertColor() {
		this.setColor(ColorUtil.BLUE);
	}
}
