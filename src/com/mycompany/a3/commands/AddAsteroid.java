package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddAsteroid extends Command{
	private GameWorld g;
	public AddAsteroid(GameWorld gw) {
		super("AddAsteroid");
		g = gw;
	}
		
	public void actionPerformed(ActionEvent E) {
			g.addAsteroid();
	}
}
