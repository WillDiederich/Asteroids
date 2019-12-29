package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AsteroidCollision extends Command{
	private GameWorld g;
	public AsteroidCollision(GameWorld gw) {
		super("AsteroidCollision");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
			g.removeAsteroid();
			g.removeAsteroid();
	}
}
