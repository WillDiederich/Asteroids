package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class KillSaucer extends Command{
	private GameWorld g;
	public KillSaucer(GameWorld gw) {
		super("KillSaucer");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
			g.removeMissile();
			g.removeFlyingSaucer();
			g.setCurrentScore(10);
	}
}
