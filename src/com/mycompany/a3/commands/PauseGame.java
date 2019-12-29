package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class PauseGame extends Command{
	private GameWorld g;
	private Game game;
	
	public PauseGame(String text, GameWorld gw, Game main) {
		super(text);
		g = gw;
		game = main;
	}
	
	public void actionPerformed(ActionEvent E) {
		if(g.isPaused() == false) {
			g.setPaused(true);
			g.setVolume(0);
			game.isPaused();
		}
		else {
			g.setPaused(false);
			if(g.getVolumeOn() == true)
				g.setVolume(25);
			game.notPaused();
		}			
	}
}
