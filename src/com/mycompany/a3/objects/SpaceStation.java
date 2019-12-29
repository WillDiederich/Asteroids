package com.mycompany.a3.objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IDrawable;
import com.mycompany.a3.Interfaces.IFixed;

public class SpaceStation extends GameObject implements IFixed, ICollider, IDrawable {
	private int blinkRate;
	private static int idNumber;
	private boolean lightOn;

	
	public SpaceStation() {
		this.blinkRate = randomNumber(1,5);
		SpaceStation.setIdNumber(SpaceStation.getIdNumber() + 1);
		this.lightOn = true;
		setColor(ColorUtil.MAGENTA);
		this.setLocationX(Math.round(randomNumber(0.0, 1024.0)*10.0)/10.0);
		this.setLocationY(Math.round(randomNumber(0.0, 768.0)*10.0)/10.0);
		this.setCrashFlag(false);

	}
	
	public static int getIdNumber() {
		return idNumber;
	}

	public static void setIdNumber(int idNumber) {
		SpaceStation.idNumber = idNumber;
	}
	
	public int getBlinkRate() {
		return this.blinkRate;
	}
	
	public void blink(boolean tf) {
		lightOn = tf;
	}
	
	@Override
	public String toString() {
		String stationOutput = "";
		stationOutput += "Space Station: " + 
				  		 "loc=("+ this.getLocationX() + "," + this.getLocationY() + ")" + 
				  		 ", color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" +	
				  		 ", Rate: " + this.blinkRate +
				  		 ", Blink: " + this.lightOn +
				  		 "\n";		
		return stationOutput;
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
			((Ship) otherObject).reload();
		}				
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		if(this.lightOn)
			g.fillArc((int)this.getLocationX(), (int)this.getLocationY(), this.getSize(), (int)1.25*this.getSize(), 0, 360);
		else
			g.drawArc((int)this.getLocationX(), (int)this.getLocationY(), this.getSize(), (int)1.25*this.getSize(), 0, 360);
	}
	
	public boolean getLightStatus() {
		return this.lightOn;
	}

}
