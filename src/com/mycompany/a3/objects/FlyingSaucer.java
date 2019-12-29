package com.mycompany.a3.objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IDrawable;
import com.mycompany.a3.Interfaces.IMovable;

public class FlyingSaucer extends GameObject implements IMovable, ICollider, IDrawable{
	private int size;
	
	public FlyingSaucer() {
		if(randomNumber(0, 2) == 0)
			this.size = 10;
		else
			this.size = 20;
		setColor(ColorUtil.GREEN);
		this.setDirection(randomNumber(0, 359));
		this.setSpeed(randomNumber(0, 4));
		this.setLocationX(Math.round(randomNumber(0.0, 1024.0)*10.0)/10.0);
		this.setLocationY(Math.round(randomNumber(0.0, 768.0)*10.0)/10.0);
		this.setCrashFlag(false);

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
		String saucerOutput = "";
		saucerOutput += "Flying Saucer: " + 
						"loc=("+ this.getLocationX() + "," + this.getLocationY() + ")" + 
						", color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" + 
						" speed=" + this.getSpeed() +
						" dir=" + this.getDirection() +
						" size=" + this.size + 
						"\n";		
		return saucerOutput;
	}

	@Override
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		int thisCenterX = (int)this.getLocationX() + (this.getSize()/2);
		int thisCenterY = (int)this.getLocationY() + (this.getSize()/2);
		int otherCenterX = (int) (((GameObject)otherObject).getLocationX() + ((GameObject)otherObject).getSize()/2); 
		int otherCenterY = (int) (((GameObject)otherObject).getLocationY() + (((GameObject)otherObject).getSize()/2));
		int distX = thisCenterX - otherCenterX;
		int distY = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (distX*distX + distY*distY);
		int thisRadius = this.getSize()/2; 
		int otherRadius = ((GameObject)otherObject).getSize()/2; 
				
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius); 

		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true; 
			} 
		return result ; 
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		if(otherObject instanceof Ship) {
			this.setCrashFlag(true);
		}
		
		if(otherObject instanceof Asteroid) {
			this.setCrashFlag(true);
		}
		
		if(otherObject instanceof Missile) {
			this.setCrashFlag(true);
		}
		
		if(otherObject instanceof FlyingSaucer) {
			this.setCrashFlag(true);
		}
		
		if(otherObject instanceof SpaceStation) {
			this.setCrashFlag(false);
		}
		
	}

	@Override
	public void draw(Graphics g) {
		move();
		g.setColor(this.getColor());
		g.fillArc((int)this.getLocationX(), (int)this.getLocationY(), this.getSize(), (int)1.25*this.getSize(), 0, 360);
	}
}
