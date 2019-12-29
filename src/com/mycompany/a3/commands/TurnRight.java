package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TurnRight extends Command{
	private GameWorld g;
	public TurnRight(GameWorld gw) {
		super("TurnRight");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
			g.turnRight();
	}
}
