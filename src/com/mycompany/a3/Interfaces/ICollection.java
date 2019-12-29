package com.mycompany.a3.Interfaces;

public interface ICollection {
	public IIterator getIterator();
	public void addObject(Object obj);
	public void removeObject(Object obj);
}
