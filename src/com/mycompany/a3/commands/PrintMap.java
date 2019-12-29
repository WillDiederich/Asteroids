package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PrintMap extends Command{
	private GameWorld g;
	public PrintMap(GameWorld gw) {
		super("PrintMap");
		g = gw;
	}
			
	public void actionPerformed(ActionEvent E) {
			g.printObjects();	
	}
}
