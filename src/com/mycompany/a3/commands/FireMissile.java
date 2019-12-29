package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class FireMissile extends Command{
	private GameWorld g;
	public FireMissile(GameWorld gw) {
		super("FireMissile");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
			g.fireMissile();
	}
}
