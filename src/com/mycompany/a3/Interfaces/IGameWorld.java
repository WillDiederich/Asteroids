package com.mycompany.a3.Interfaces;

import com.mycompany.a3.objects.ObjectCollection;

public interface IGameWorld {
	public int getRemainingLives();
	public int getCurrentScore();
	public int getElapsedTime();
	public int getMissileCount();
	public int currentScore();
	public String getStatus();
	public ObjectCollection getObjectCollection();
	public boolean getSoundStatus();
	public String printObjects();
}
