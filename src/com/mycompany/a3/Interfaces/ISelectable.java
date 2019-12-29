package com.mycompany.a3.Interfaces;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface ISelectable {
	public boolean isSelected();
	public void setSelected(int pPtrRelPrnt, int pCmpRelPrnt);
	void setSelectedFalse();
	public void revertColor();
}
