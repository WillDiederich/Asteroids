package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddSpaceStation extends Command{
	private GameWorld g;
	public AddSpaceStation(GameWorld gw) {
		super("    AddSpaceStation    ");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
				g.addSpaceStation();
	}
}
