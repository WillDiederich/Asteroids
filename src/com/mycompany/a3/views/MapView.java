package com.mycompany.a3.views;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Interfaces.IDrawable;
import com.mycompany.a3.Interfaces.IGameWorld;
import com.mycompany.a3.Interfaces.IIterator;
import com.mycompany.a3.Interfaces.ISelectable;
import com.mycompany.a3.objects.FlyingSaucer;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.ObjectCollection;
import com.mycompany.a3.objects.Ship;

public class MapView extends Container implements Observer{
	GameWorld gamew;
	IGameWorld gwProxy;
	private int pX; 
	private int pY;
	ObjectCollection collection;

	public MapView(GameWorld gw) {
		this.setLayout(new FlowLayout(CENTER));
		this.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.YELLOW));
		this.getAllStyles().setBgTransparency(0);
		gamew = gw;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		ObjectCollection objectCollection = gwProxy.getObjectCollection();
		IIterator iterator = objectCollection.getIterator();
		Object currentObject = new Object();
		while(iterator.hasNext()){
			currentObject = iterator.getNext();
			if(currentObject instanceof IDrawable) {
				((IDrawable)currentObject).draw(g);
			}
		}
	}
	
	@Override
	public void update(Observable o, Object data) {
		gwProxy = (IGameWorld)o;
		String mapOutput = ((IGameWorld) data).printObjects();
		System.out.println(mapOutput);
		repaint();
	}

	@Override
	public void pointerPressed(int x,int y){
		pX = x;
		pY = y;
		ObjectCollection collection = gwProxy.getObjectCollection();
		IIterator iterator = collection.getIterator();
		if(gamew.isPaused() == true) {
			while(iterator.hasNext()) {
				Object obj = iterator.getNext();
				if(obj instanceof ISelectable) {
					((ISelectable) obj).setSelected(pX, pY);
					if(((ISelectable) obj).isSelected() == true) {
						repaint();
					}
				}
			}
		}
		else {
			while(iterator.hasNext()) {
				Object obj = iterator.getNext();
				if(obj instanceof ISelectable) {
					((ISelectable) obj).setSelectedFalse();
				}
			}
		}	
	}
}
