package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ReloadMissiles extends Command{
	private GameWorld g;
	public ReloadMissiles(GameWorld gw) {
		super("ReloadMissiles");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
		g.reloadMissiles();
	}
}
