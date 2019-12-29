package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Quit extends Command{
	public Quit(GameWorld gw) {
		super("Quit");
	}
			
	public void actionPerformed(ActionEvent E) {
		Boolean bOk = Dialog.show("Confirm Quit", "Are you sure you want to quit?", "Yes", "Cancel");
		if(bOk)
			Display.getInstance().exitApplication();
	}
}
