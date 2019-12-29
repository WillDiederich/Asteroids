package com.mycompany.a3;

import java.util.Observable;

import com.mycompany.a3.Interfaces.IGameWorld;
import com.mycompany.a3.objects.ObjectCollection;

public class GameWorldProxy extends Observable implements IGameWorld{
	private GameWorld gameWorld;
	public GameWorldProxy(GameWorld gw) {
		gameWorld = gw;
	}
	@Override
	public int getRemainingLives() {
		return gameWorld.getRemainingLives();
	}

	@Override
	public int getCurrentScore() {
		// TODO Auto-generated method stub
		return gameWorld.getCurrentScore();
	}

	@Override
	public int getElapsedTime() {
		// TODO Auto-generated method stub
		return gameWorld.getElapsedTime();
	}

	@Override
	public int getMissileCount() {
		// TODO Auto-generated method stub
		return gameWorld.getMissileCount();
	}

	@Override
	public int currentScore() {
		// TODO Auto-generated method stub
		return gameWorld.getCurrentScore();
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return gameWorld.getStatus();
	}

	@Override
	public ObjectCollection getObjectCollection() {
		// TODO Auto-generated method stub
		return gameWorld.getObjectCollection();
	}
	
	@Override
	public boolean getSoundStatus() {
		return gameWorld.getSoundStatus();
	}
	
	@Override
	public String printObjects() {
		return gameWorld.printObjects();
	}
}
