package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Jump extends Command{
	private GameWorld g;
	public Jump(GameWorld gw) {
		super("Jump");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
			g.jump();
	}
}
