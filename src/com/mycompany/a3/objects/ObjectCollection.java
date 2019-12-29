package com.mycompany.a3.objects;
import java.util.ArrayList;

import com.mycompany.a3.Interfaces.ICollection;
import com.mycompany.a3.Interfaces.IIterator;

public class ObjectCollection implements ICollection{
	private ArrayList<Object> objectCollection;
	
	public ObjectCollection(){
		objectCollection = new ArrayList<Object>();
	}
	public void addObject(Object obj){
		objectCollection.add(obj);
	}
	public void removeObject(Object obj) {
		objectCollection.remove(obj);
	}
	
	public void removeObject(int obj) {
		objectCollection.remove(obj);
	}
	
	public Object getObject(int x) {
		return objectCollection.get(x);
	}
	
	public String printObjectCollection() {
		String objects = "";
		for(int x = 0; x <= objectCollection.size()-1; x++) {
			objects += objectCollection.get(x);
		}
		return objects;
	}
	public int size() {
		return objectCollection.size();
	}
	@Override
	public IIterator getIterator() {
		return new arrayIterator();
	}
	
	public class arrayIterator implements IIterator{
		private int currentObject;
		
		public arrayIterator() {
			currentObject = -1;
		}
		
		public boolean hasNext() {
			if(objectCollection.size() <= 0)
				return false;
			if(currentObject == objectCollection.size()-1)
				return false;
			return true;
		}

		public Object getNext() {
			currentObject++;
			return objectCollection.get(currentObject);
		}

		
		
	}
	
}
