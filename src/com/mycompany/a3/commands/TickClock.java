package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TickClock extends Command{
	private GameWorld g;
	public TickClock(GameWorld gw) {
		super("TickClock");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
		g.incrementClock();		
	}
}
