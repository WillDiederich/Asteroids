package com.mycompany.a3.views;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.Interfaces.IGameWorld;

public class PointsView extends Container implements Observer{
	private Label livesRemaining;
	private Label elapsedTime;
	private Label missileCount;
	private Label sound;
	private Label points;
	
	public PointsView() {
		this.setLayout(new FlowLayout(CENTER));
		this.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.CYAN));
		this.getAllStyles().setBgTransparency(0);
		livesRemaining = new Label("Lives: 0");
		livesRemaining.getAllStyles().setPadding(Component.LEFT, 5);
		livesRemaining.getAllStyles().setPadding(Component.RIGHT, 5);
		elapsedTime = new Label("Time: 0");
		elapsedTime.getAllStyles().setPadding(Component.LEFT, 5);
		elapsedTime.getAllStyles().setPadding(Component.RIGHT, 5);
		points = new Label("Score: 0");
		points.getAllStyles().setPadding(Component.LEFT, 5);
		points.getAllStyles().setPadding(Component.RIGHT, 5);
		missileCount = new Label("Missiles: 0");
		missileCount.getAllStyles().setPadding(Component.LEFT, 5);
		missileCount.getAllStyles().setPadding(Component.RIGHT, 5);
		sound = new Label("Sound: On");
		sound.getAllStyles().setPadding(Component.LEFT, 5);
		sound.getAllStyles().setPadding(Component.RIGHT, 5);
		
		this.add(points);
		this.add(livesRemaining);
		this.add(missileCount);
		this.add(sound);
		this.add(elapsedTime);
	}
	

	@Override
	public void update(Observable observable, Object data) {
		int updatePoints = ((IGameWorld) data).getCurrentScore();
		int updateLives = ((IGameWorld) data).getRemainingLives();
		int updateMissile = ((IGameWorld) data).getMissileCount();
		int updateTime = ((IGameWorld) data).getElapsedTime();
		boolean updateSound = ((IGameWorld) data).getSoundStatus();
	
		points.setText("Score: " + updatePoints);
		livesRemaining.setText("Lives: " + updateLives);
		missileCount.setText("Missiles: " + updateMissile);
		sound.setText("Sound: " + updateSound);
		elapsedTime.setText("Time: " + updateTime);
	}
	
}
