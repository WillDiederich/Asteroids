package com.mycompany.a3.buttons;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.mycompany.a3.commands.Accelerate;
import com.mycompany.a3.commands.Decelerate;

public class Buttons extends Button{
	public Buttons(String label, Command cmd) {
		super(label);
		
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setMargin(1, 1, 1, 1);
		this.getAllStyles().setPadding(Component.TOP, 1);
		this.getAllStyles().setPadding(Component.BOTTOM, 1);
		
		this.getSelectedStyle().setBgTransparency(255);
		this.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		
		this.getSelectedStyle().setBgTransparency(255);
		this.getSelectedStyle().setBgColor(ColorUtil.GRAY);
		this.getSelectedStyle().setFgColor(ColorUtil.WHITE);
		
		this.setCommand(cmd);
	}
}
