package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TurnLeft extends Command{
	private GameWorld g;
	public TurnLeft(GameWorld gw) {
		super("TurnLeft");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
			g.turnLeft();
	}
}
