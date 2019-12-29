package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CrashedSaucer extends Command{
	private GameWorld g;
	public CrashedSaucer(GameWorld gw) {
		super("CrashedSaucer");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
			g.removeShip();
			g.removeFlyingSaucer();
			g.setRemainingLives();
	}
}
