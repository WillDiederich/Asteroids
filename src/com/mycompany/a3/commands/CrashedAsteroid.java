package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CrashedAsteroid extends Command{
	private GameWorld g;
	public CrashedAsteroid(GameWorld gw) {
		super("CrashedAsteroid");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
			g.removeShip();
			g.removeAsteroid();
			g.setRemainingLives();
	}
}
