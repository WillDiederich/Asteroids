package com.mycompany.a3;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.buttons.Buttons;
import com.mycompany.a3.commands.*;
import com.mycompany.a3.views.MapView;
import com.mycompany.a3.views.PointsView;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;

public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	
	//Commands
	private Accelerate i;
	private AddAsteroid a;
	private AddFlyingSaucer y;
	private AddShip s;
	private AddSpaceStation b;
	private AsteroidCollision x;
	private AsteroidSaucerCollision w;
	private CrashedAsteroid c;
	private CrashedSaucer h;
	private Decelerate d;
	private FireMissile f;
	private Jump j;
	private KillAsteroid k;
	private KillSaucer e;
	private PrintGameState p;
	private PrintMap m;
	private Quit q;
	private ReloadMissiles n;
	private TickClock t;
	private TurnLeft l;
	private TurnRight r;
	private PauseGame z;
	private UITimer timer;
	
	private Container westContainer;
	
	//Buttons
	public Buttons b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23;
	
	
	public Game(){
		
		timer = new UITimer(this);
		startTime(timer);
		gw = new GameWorld();
		mv = new MapView(gw);
		pv = new PointsView();
		gw.setObserver(pv);
		gw.setObserver(mv);
	
		
		Command newGame = new Command("New");
		Command saveGame = new Command("Save");
		Command undo = new Command("Undo");
		
		About about = new About();
		Quit quit = new Quit(gw);
		
		CheckBox sound = new CheckBox("Sound");
		sound.getAllStyles().setBgTransparency(255);
		Command soundCommand = new Sound(sound, gw);
		sound.setCommand(soundCommand);
	
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, pv);
		this.add(BorderLayout.CENTER, mv);
		//West Container
		westContainer = new Container();
		westContainer.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		westContainer.getAllStyles().setBgTransparency(255);
		westContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		westContainer.getAllStyles().setPadding(Component.TOP, 5);
		westContainer.getAllStyles().setPadding(Component.BOTTOM, 5);
		westContainer.getAllStyles().setPadding(Component.LEFT, 5);
		westContainer.getAllStyles().setPadding(Component.RIGHT, 5);
		westContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.setScrollableY(true);
		this.add(BorderLayout.WEST, westContainer);
		
		//BUTTONS
		a = new AddAsteroid(gw);
		b1 = new Buttons("Add Asteroid", a);
		westContainer.add(b1);

		y = new AddFlyingSaucer(gw);
		b2 = new Buttons("Add Saucer", y);
		westContainer.add(b2);

		b = new AddSpaceStation(gw);
		b3 = new Buttons("Add Space Station", b);
		westContainer.add(b3);

		s = new AddShip(gw);
		b4 = new Buttons("Add Ship", s);
		westContainer.add(b4);

		i = new Accelerate(gw);
		b5 = new Buttons("Accelerate", i);
		westContainer.add(b5);

		d = new Decelerate(gw);
		b6 = new Buttons("Decelerate", d);
		westContainer.add(b6);

		l = new TurnLeft(gw);
		b7 = new Buttons("Turn Left", l);
		westContainer.add(b7);

		r = new TurnRight(gw);
		b8 = new Buttons("Turn Right", r);
		westContainer.add(b8);

		f = new FireMissile(gw);
		b9 = new Buttons("Fire Missile", f);
		westContainer.add(b9);

		j = new Jump(gw);
		b10 = new Buttons("Jump", j);
		westContainer.add(b10);

		q = new Quit(gw);
		b21 = new Buttons("Quit", q);
		westContainer.add(b21);
		
		z = new PauseGame("Pause", gw, this);
		b22 = new Buttons("Pause", z);
		westContainer.add(b22);
		
		//Toolbar
		Toolbar myToolbar = new Toolbar();
		this.setToolbar(myToolbar);
		myToolbar.setTitle("Asteroids");
		
		myToolbar.addComponentToSideMenu(sound);
		myToolbar.addCommandToSideMenu(newGame);
		myToolbar.addCommandToSideMenu(saveGame);
		myToolbar.addCommandToSideMenu(undo);
		myToolbar.addCommandToSideMenu(about);
		myToolbar.addCommandToSideMenu(quit);
				
		myToolbar.addCommandToOverflowMenu(a); 	
		myToolbar.addCommandToOverflowMenu(b);
		myToolbar.addCommandToOverflowMenu(s);
		myToolbar.addCommandToOverflowMenu(n);
		myToolbar.addCommandToOverflowMenu(k);
		myToolbar.addCommandToOverflowMenu(c);
		myToolbar.addCommandToOverflowMenu(x);
		myToolbar.addCommandToOverflowMenu(t);
		myToolbar.addCommandToOverflowMenu(q);
		
		//Key entry
		this.addKeyListener('s', i);
		this.addKeyListener('a', l);
		this.addKeyListener('w', d);
		this.addKeyListener('d', r);
		this.addKeyListener('f', f);
		this.addKeyListener('j', j);	
		
		show();

		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
		gw.setOriginX(mv.getAbsoluteX());
		gw.setOriginY(mv.getAbsoluteY());
		gw.init();
		
		System.out.println(gw.getOriginX() + ", " + gw.getOriginY());
		System.out.println(gw.getWidth() + ", " + gw.getHeight());
	}	
	
	@Override
	public void run() {
		gw.incrementClock();
		if(gw.getRemainingLives() == 0) {
			this.stopTime(timer);
			Dialog.show("Game Over", "Score: " + gw.getCurrentScore(), "Ok", "Quit");
		}
	}
	
	public void startTime(UITimer t) {
		t.schedule(20, true, this);
	}
	
	public void stopTime(UITimer t) {
		t.cancel();
	}

	public void notPaused() {
		startTime(timer);
		b1.setEnabled(true);
		b2.setEnabled(true);
		b3.setEnabled(true);
		b4.setEnabled(true);
		b5.setEnabled(true);
		b6.setEnabled(true);
		b7.setEnabled(true);
		b8.setEnabled(true);
		b9.setEnabled(true);
		b10.setEnabled(true);
		b22.setText("pause");
		this.addKeyListener('w', i);
		this.addKeyListener('a', l);
		this.addKeyListener('s', d);
		this.addKeyListener('d', r);
		this.addKeyListener('f', f);
		this.addKeyListener('j', j);
	}
	
	public void isPaused() {
		stopTime(timer);
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
		b5.setEnabled(false);
		b6.setEnabled(false);
		b7.setEnabled(false);
		b8.setEnabled(false);
		b9.setEnabled(false);
		b10.setEnabled(false);
		b22.setText("Play");
		this.removeKeyListener('w', i);
		this.removeKeyListener('a', l);
		this.removeKeyListener('s', d);
		this.removeKeyListener('d', r);
		this.removeKeyListener('f', f);
		this.removeKeyListener('j', j);
	}
	
}
