package com.mycompany.a3.commands;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class About extends Command{
	public About() {
		super("About");
	}
	
	public void actionPerformed(ActionEvent E) {
		String s = "William Diederich \nCSC-133 \nAssignment 2";
		Dialog.show("About", s, "Close", null);
	}
}
