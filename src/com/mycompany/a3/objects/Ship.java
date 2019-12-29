package com.mycompany.a3.objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IDrawable;
import com.mycompany.a3.Interfaces.IMovable;
import com.mycompany.a3.Interfaces.ISelectable;
import com.mycompany.a3.Interfaces.ISteerable;

public class Ship extends GameObject implements IMovable, ISteerable, IDrawable, ICollider {
	private int missileCount;
	private int startingLocationX;
	private int startingLocationY;
	private boolean reloadFlag;
	
	public Ship(){
		missileCount = 12;
		setColor(ColorUtil.BLACK);
		setSpeed(0);
		setDirection(0);
		setCrashFlag(false);
	}
	
	public void draw(Graphics g) {
		move();
		g.setColor(this.getColor());
		int xpoints[] = {(int)this.getLocationX() + 10, (int)this.getLocationX() + 25, (int)this.getLocationX() + 35};
	    int ypoints[] = {(int)this.getLocationY() + 80, (int)this.getLocationY() + 40, (int)this.getLocationY() + 80};
	    int npoints = 3;
		g.fillPolygon(xpoints, ypoints, npoints);
	}
		
	public void accelerate(){
		this.setSpeed(this.getSpeed()+1);
	}
	
	public void decelerate(){
		this.setSpeed(this.getSpeed()-1);
	}
	
	public void turnRight(){
		if(this.getDirection() - 10 < 0)
			this.setDirection(this.getDirection() - 10 + 359);
		
		else
			this.setDirection(this.getDirection()-10);
	}
	
	public void turnLeft(){
		if(this.getDirection() + 10 > 359 )
			this.setDirection(this.getDirection()+10 - 359);
		else
			this.setDirection(this.getDirection() + 10);
	}
	
	public void jump(){
		this.setLocationX(this.getStartingLocationX());
		this.setLocationY(this.getStartingLocationY());
		
	}
	
	public void fireMissile(){
		this.missileCount--;
	}
	
	public void reload(){
		this.missileCount = 12;
	}
	
	public int getMissileCount(){
		return this.missileCount;
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
		String shipOutput = "";
		shipOutput += "Ship: " + 
					  "loc=("+ this.getLocationX() + "," + this.getLocationY() + ")" + 
					  ", color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" +
					  ", speed=" + this.getSpeed() +
					  ", dir=" + this.getDirection() +
					  ", missiles=" + this.getMissileCount() + 
					  "\n";		
		return shipOutput;
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
		if(otherObject instanceof Asteroid) {
			this.setCrashFlag(true);
		}
		
		if(otherObject instanceof Missile) {
			this.setCrashFlag(false);
		}
		
		if(otherObject instanceof FlyingSaucer) {
			this.setCrashFlag(true);
		}
		
		if(otherObject instanceof SpaceStation) {
			this.reload();
		}
	}

	public int getStartingLocationX() {
		return startingLocationX;
	}

	public void setStartingLocationX(int startingLocationX) {
		this.startingLocationX = startingLocationX;
	}

	public int getStartingLocationY() {
		return startingLocationY;
	}

	public void setStartingLocationY(int startingLocationY) {
		this.startingLocationY = startingLocationY;
	}
}