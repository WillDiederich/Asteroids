package com.mycompany.a3.commands;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Sound extends Command{
	private CheckBox checkBox;
	private GameWorld g;
	
	public Sound(CheckBox check, GameWorld gw) {
		super("sound");
		checkBox = check;
		g = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(checkBox.isSelected()) {
			g.setSoundStatus();
			g.setVolume(0);
			g.setVolumeOn(false);
		}
		else {
			g.setSoundStatus();
			g.setVolume(25);
			g.setVolumeOn(true);
		}
	}

}
