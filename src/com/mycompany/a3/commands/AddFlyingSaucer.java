package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddFlyingSaucer extends Command{
	private GameWorld g;
	public AddFlyingSaucer(GameWorld gw) {
		super("AddFlyingSaucer");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
				g.addFlyingSaucer();
	}
}
