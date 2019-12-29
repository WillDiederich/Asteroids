package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PrintGameState extends Command{
	private GameWorld g;
	public PrintGameState(GameWorld gw) {
		super("PrintGameState");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
		System.out.println(g.getStatus());
	}
}
