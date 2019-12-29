package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IFixed;
import com.mycompany.a3.Interfaces.IGameWorld;
import com.mycompany.a3.Interfaces.IIterator;
import com.mycompany.a3.Interfaces.IMovable;
import com.mycompany.a3.objects.Asteroid;
import com.mycompany.a3.objects.FlyingSaucer;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.Missile;
import com.mycompany.a3.objects.ObjectCollection;
import com.mycompany.a3.objects.Ship;
import com.mycompany.a3.objects.SpaceStation;
import com.mycompany.a3.sound.GameSound;
import com.mycompany.a3.views.MapView;
import com.mycompany.a3.views.PointsView;

public class GameWorld extends Observable implements IGameWorld{
	//Initialize starting game objects
	private ObjectCollection objectCollection;
	private Ship ship;
	
	private MapView mv;
	private PointsView pv;
	private GameSound gameSound;
	
	//Initialize starting game variables
	private int currentTick;
	private int currentScore;
	private int tickTime;
	private int elapsedTime;
	private int lives;
	private int asteroidCount;
	private int flyingSaucerCount;
	private int volume;
	private boolean shipExists;
	private boolean sound;
	
	//Initialize playable area
	private int gameWidth;
	private int gameHeight;
	private int originX;
	private int originY;
	private int iPx;
	private boolean isPaused;
	private boolean volumeOn;
	
	//Constructor for the GameWorld object
	public GameWorld() {
		objectCollection = new ObjectCollection();
		gameSound = new GameSound();
		isPaused = false;
	}
	
	//Initialize the GameWorld, initializes variables
	public void init() {
		currentScore = 0;
		elapsedTime = 0;
		volume = 25;
		lives = 3;
		sound = true;
		addShip();
		addSpaceStation();
		addAsteroid();
		addAsteroid();
		addAsteroid();
		gameSound.playMusic(this.getVolume());
	}
	
	//Gets current game status
	public String getStatus() {
		return "Current Score: " + this.currentScore + 
				"\nLives Remaining: " + this.lives + 
				"\nMissiles Remaining: " + ship.getMissileCount() + 
				"\nTime Played: " + this.elapsedTime;
	}
	
	//Prints current contents of the objectCollection
	public String printObjects() {
		return objectCollection.printObjectCollection();
	}
	
	//Player death handler
	public boolean playerDies(){
		if(lives > 0){
			this.lives--;
			observerUpdate();
			return true;
		}
		else {
			observerUpdate();
			return false;
		}
	}
	
	//Game clock incrementor
	public void incrementClock() {
		IIterator iterator = objectCollection.getIterator();
		Object obj;
		setCurrentTick(getCurrentTick() + 20);
		

		
		if(currentTick == 1000) {
			elapsedTime += 1;
			currentTick = 0;
			if(elapsedTime > 0 && elapsedTime % 5 == 0 && asteroidCount < 10) {
				addAsteroid();
			}
			if(elapsedTime > 0 && elapsedTime % 15 == 0 && flyingSaucerCount < 2) {
				addFlyingSaucer();
			}
		}

		for(int x = 0; x <= objectCollection.size() - 1; x++) {
			obj = iterator.getNext();
			if(obj instanceof Missile) {
				if(((Missile) obj).getFuelReserve() == 0) {
					objectCollection.removeObject(obj);
				}
				else {
					if(((Missile) obj).getLocationX() <= 685)
						((Missile) obj).setDirection(((Missile) obj).getDirection() + 180);
					if(((GameObject) obj).getLocationX() >= 1363+685)
						((GameObject) obj).setDirection(((GameObject) obj).getDirection() + 180);
					if(((GameObject) obj).getLocationY() <= 230)
						((GameObject) obj).setDirection(((GameObject) obj).getDirection() + 180);
					if(((GameObject) obj).getLocationY() >= 2311+200)
						((GameObject) obj).setDirection(((GameObject) obj).getDirection() + 180);
					((Missile) obj).move();
				}
			}
			else if(obj instanceof IMovable) {
				((IMovable) obj).move();
				if(((GameObject) obj).getLocationX() <= 685)
					((GameObject) obj).setDirection(((GameObject) obj).getDirection() + 180);
				if(((GameObject) obj).getLocationX() >= 1363+650)
					((GameObject) obj).setDirection(((GameObject) obj).getDirection() + 180);
				if(((GameObject) obj).getLocationY() <= 230)
					((GameObject) obj).setDirection(((GameObject) obj).getDirection() + 180);
				if(((GameObject) obj).getLocationY() >= 2311+200)
					((GameObject) obj).setDirection(((GameObject) obj).getDirection() + 180);
			}
			else if(obj instanceof IFixed) {
				if((elapsedTime % ((IFixed) obj).getBlinkRate() != 0)) {
					((IFixed) obj).blink(true);
				}
				else if((elapsedTime % ((IFixed) obj).getBlinkRate() == 0)) {
					((IFixed) obj).blink(false);
				}
			}
		}
		checkCollisions();
		observerUpdate();
	}
	
	public void checkCollisions() {
		IIterator iterator = objectCollection.getIterator();
		while(iterator.hasNext() ){
			ICollider curObj = (ICollider)iterator.getNext(); 
			IIterator iter2 = objectCollection.getIterator(); 
			while(iter2.hasNext()){ 
				ICollider otherObj = (ICollider) iter2.getNext(); 
				if(otherObj!=curObj){
					if(curObj.collidesWith(otherObj)){ 
						curObj.handleCollision(otherObj); 
					} 
				}
			}
		}
		removeCollided();
		observerUpdate();
	}
	
	public void removeCollided() {
		ObjectCollection collection = new ObjectCollection();
		IIterator iterator = objectCollection.getIterator();
		Object remove = new Object();
		int i = 0;
		while(iterator.hasNext()) {
			remove = iterator.getNext();
			i++;
			if(((GameObject)remove).isCrashFlag() == true) {
						collection.addObject(remove);
			}
		}
		remover(collection);
		
	}
	
	public void remover(ObjectCollection collection) {
		Random rand = new Random();
		IIterator iterator = collection.getIterator();
		while(iterator.hasNext()) {
			Object target = iterator.getNext();
			if(target instanceof Asteroid) {
				objectCollection.removeObject(target);
				this.asteroidCount--;
		        if(((GameObject) target).getSize() >= 6 && ((GameObject) target).getSize() <= 18) {
		            this.setCurrentScore(5);
		        }
		        if(((GameObject) target).getSize() >= 19 && ((GameObject) target).getSize() <= 24) {
		            this.setCurrentScore(4);
		            int minSize = 6;
		            int maxSize = ((GameObject) target).getSize();
		            int size1 = rand.nextInt(maxSize-(minSize*2))+6;
		            int size2 = maxSize - size1;
		            addAsteroid(size1);
		            addAsteroid(size2);
		        }
		        if(((GameObject) target).getSize() >= 25 && ((GameObject) target).getSize() <=40) {           
		            this.setCurrentScore(3);
		            int minSize = 6;
		            int maxSize = ((GameObject) target).getSize();
		            int size1 = rand.nextInt(maxSize-(minSize*3)) + 6;
		            maxSize = maxSize - size1;
		            int size2 = rand.nextInt(maxSize-(minSize*2)) + 6;
		            int size3 = maxSize - size2;
		            addAsteroid(size1);
		            addAsteroid(size2);
		            addAsteroid(size3);
		        }
			}
			
			if(target instanceof Ship) {
				removeShip();
				playerDies();
				addShip();
			}
			
			if(target instanceof Missile) {
				gameSound.MissileHitSound(this.getVolume());
				objectCollection.removeObject(target);
			}
			
			if(target instanceof FlyingSaucer) {
				this.setCurrentScore(8);
				gameSound.MissileHitSound(this.getVolume());
				objectCollection.removeObject(target);
			}
		}
	}
	
	//accessors for sound
	public boolean getSoundStatus() {
		return sound;
	}
	public void setSoundStatus() {
		sound = !sound;
		observerUpdate();
	}
	//Accessors for elapsedTime
	public int getElapsedTime() {
		return elapsedTime;
	}
	//Accessors for missileCount
	public int getMissileCount() {
		return ship.getMissileCount();
	}
	
	
	//accessors for objectCollection
	public ObjectCollection getObjectCollection() {
		return objectCollection;
	}
	
	//Accessors for currentTick
	public int getCurrentTick() {
		return currentTick;
	}

	public void setCurrentTick(int currentTick) {
		this.currentTick = currentTick;
	}
	
	//Accessors for currentScore
	public int currentScore() {
		return this.currentScore;
	}
	public int getCurrentScore() {
		return currentScore;
	}
	public void setCurrentScore(int amount) {
		currentScore += amount;
	}
	
	//Accessors for currentLives
	public int getRemainingLives() {
		return this.lives;
	}
	
	public void setRemainingLives() {
		this.lives--;
	}
	
	//Accessors for width/height & origin
	public void setHeight(int height) {
		this.gameHeight = height;
	}
	
	public int getHeight() {
		return this.gameHeight;
	}
	
	public void setWidth(int width) {
		this.gameWidth = width;
	}
	
	public int getWidth() {
		return this.gameWidth;
	}
	
	public int getOriginX() {
		return originX;
	}

	public void setOriginX(int originX) {
		this.originX = originX;
	}

	public int getOriginY() {
		return originY;
	}

	public void setOriginY(int originY) {
		this.originY = originY;
	}
	//Accessors for shipExists
	public boolean getShipStatus() {
		return this.shipExists;
	}
	
	public void setShipStatus(Boolean status) {
		this.shipExists = status;
	}
	
	//Player ship functionality
	public void addShip() {
		if(getShipStatus()) {
			System.out.println("There is already a ship in the world.");
		}
		else if(getRemainingLives() > 0) {
			ship = new Ship();
			ship.setSize(50);
			ship.setLocationX(this.getWidth());
			ship.setLocationY(this.getHeight()/2);
			ship.setStartingLocationX(this.getWidth());
			ship.setStartingLocationY(this.getHeight()/2);
			objectCollection.addObject(ship);
			setShipStatus(true);
		}
		else
			System.out.println("Out of lives.");
		observerUpdate();
		System.out.println(ship.getSize());
	}
	
	public void accelerate() {
		if(getShipStatus())
			ship.accelerate();
		else
			System.out.println("There is no ship in the world.");
		observerUpdate();
	}
	
	public void decelerate() {
		if(getShipStatus())
			ship.decelerate();
		else
			System.out.println("There is no ship in the world.");
		observerUpdate();
	}
	
	public void turnRight() {
		if(getShipStatus())
			ship.turnRight();
		else
			System.out.println("There is no ship in the world.");
		observerUpdate();
	}
	
	public void turnLeft() {
		if(getShipStatus())
			ship.turnLeft();
		else
			System.out.println("There is no ship in the world.");
		observerUpdate();
	}
	
	public void jump(){
		if(getShipStatus())
			ship.jump();
		else
			System.out.println("There is no ship in the world.");
		observerUpdate();
	}
	
	public void removeShip(){
		if(getShipStatus()) {
			objectCollection.removeObject(ship);
			setShipStatus(false);
		}
		else
			System.out.println("There is no ship in the world.");
		observerUpdate();
	}
	
	public void fireMissile() {
		if(getShipStatus())
			if(ship.getMissileCount() >= 1) {
				gameSound.fireMissileSound(this.getVolume());
				ship.fireMissile();
				addMissile();
			}
			else
				System.out.println("Out of Missiles");
		else
			System.out.println("There is no ship in the world.");
		observerUpdate();
	}
	
	public void reloadMissiles(){
		if(getShipStatus())
			ship.reload();
		else
			System.out.println("There is no ship in the world.");
		observerUpdate();
	}
	
	//Missile functionality
	public void addMissile() {
		Missile missile = new Missile();
		missile.setSpeed(ship.getSpeed() + 25);
		missile.setDirection(ship.getDirection() - 180);
		missile.setLocationX(ship.getLocationX()+15);
		missile.setLocationY(ship.getLocationY());
		missile.setSize(25);
		objectCollection.addObject(missile);
		observerUpdate();
	}
	
	public void removeMissile(){
		IIterator iterator = objectCollection.getIterator();
		Object currentObject = new Object();
		while(iterator.hasNext()){
			currentObject = iterator.getNext();
			if(currentObject instanceof Missile) {
				objectCollection.removeObject(currentObject);	
				break;
			}
		}
		observerUpdate();
	}
	
	//Asteroid functionality
	public void addAsteroid() {
		Random rand = new Random();
		Asteroid asteroid = new Asteroid();
		asteroid.setLocationX(Math.round(rand.nextDouble() * (this.getWidth() + (this.getWidth()/2) - this.getOriginX()) + this.getOriginX()));		
		asteroid.setLocationY(Math.round(rand.nextDouble() * (this.getHeight() - this.getOriginY()) + this.getOriginY()));
		objectCollection.addObject(asteroid);
		this.asteroidCount++;
		observerUpdate();
	}
	
	public void addAsteroid(int size) {
		Random rand = new Random();
		Asteroid asteroid = new Asteroid(size);
		asteroid.setLocationX(Math.round(rand.nextDouble() * (this.getWidth() + (this.getWidth()/2) - this.getOriginX()) + this.getOriginX()));		
		asteroid.setLocationY(Math.round(rand.nextDouble() * (this.getHeight() - this.getOriginY()) + this.getOriginY()));
		objectCollection.addObject(asteroid);
		this.asteroidCount++;
		observerUpdate();
	}
	
	 public void removeAsteroid(){
	        IIterator iterator = objectCollection.getIterator();
	        Asteroid asteroid;
	        int size = 0;
	        Random rand = new Random();
	        Object currentObject = new Object();
	        while(iterator.hasNext()){
	            currentObject = iterator.getNext();
	            if(currentObject instanceof Asteroid) {
	                asteroid = (Asteroid) currentObject;
	                size = asteroid.getSize();
	                objectCollection.removeObject(currentObject);    
	                break;
	            }
	        }
	        asteroid = null;
	        observerUpdate();
	        if(size >= 6 && size <= 18) {
	            this.setCurrentScore(5);
	        }
	        if(size >= 19 && size <= 24) {
	            this.setCurrentScore(4);
	            int minSize = 6;
	            int maxSize = size;
	            int size1 = rand.nextInt(maxSize-(minSize*2))+6;
	            int size2 = maxSize - size1;
	            addAsteroid(size1);
	            addAsteroid(size2);
	        }
	        if(size >= 25 && size <=40) {           
	            this.setCurrentScore(3);
	            int minSize = 6;
	            int maxSize = size;
	            int size1 = rand.nextInt(maxSize-(minSize*3)) + 6;
	            maxSize = maxSize - size1;
	            int size2 = rand.nextInt(maxSize-(minSize*2)) + 6;
	            int size3 = maxSize - size2;
	            addAsteroid(size1);
	            addAsteroid(size2);
	            addAsteroid(size3);
	        }
	    }
	
	//Flying Saucer functionality
	public void addFlyingSaucer() {
		Random rand = new Random();
		FlyingSaucer saucer = new FlyingSaucer();
		saucer.setLocationX(Math.round(rand.nextDouble() * (this.getWidth() + (this.getWidth()/2) - this.getOriginX()) + this.getOriginX()));		
		saucer.setLocationY(Math.round(rand.nextDouble() * (this.getHeight() - this.getOriginY()) + this.getOriginY()));
		saucer.setSize(75);
		objectCollection.addObject(saucer);
		flyingSaucerCount++;
		observerUpdate();

	}
	
	public void removeFlyingSaucer(){
		IIterator iterator = objectCollection.getIterator();
		Object currentObject = new Object();
		while(iterator.hasNext()){
			currentObject = iterator.getNext();
			if(currentObject instanceof FlyingSaucer) {
				objectCollection.removeObject(currentObject);	
				break;
			}
		}
		observerUpdate();
	}
	
	//Space Station functionality
	public void addSpaceStation() {
		Random rand = new Random();
		SpaceStation station = new SpaceStation();
		station.setLocationX(Math.round(rand.nextDouble() * (this.getWidth() + (this.getWidth()/2) - this.getOriginX()) + this.getOriginX()));		
		station.setLocationY(Math.round(rand.nextDouble() * (this.getHeight() - this.getOriginY()) + this.getOriginY()));
		station.setSize(125);
		objectCollection.addObject(station);
		observerUpdate();
	}
	
	public void removeSpaceStation(){
		IIterator iterator = objectCollection.getIterator();
		Object currentObject = new Object();
		while(iterator.hasNext()){
			currentObject = iterator.getNext();
			if(currentObject instanceof SpaceStation) {
				objectCollection.removeObject(currentObject);	
				break;
			}
		}
		observerUpdate();
	}

	public void setObserver(Observer o) {
		this.addObserver(o);
	}
	
	public void observerUpdate() {
		GameWorldProxy proxy = new GameWorldProxy(this);
		this.setChanged();
		this.notifyObservers(proxy);
	}

	public int getiPx() {
		return iPx;
	}

	public void setiPx(int iPx) {
		this.iPx = iPx;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
		if(volume == 0) {
			gameSound.pauseMusic();
		}
		else
			gameSound.playMusic(this.getVolume());
	}

	public boolean getVolumeOn() {
		return volumeOn;
	}

	public void setVolumeOn(boolean volumeOn) {
		this.volumeOn = volumeOn;
	}

	public int getTickTime() {
		return tickTime;
	}

	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}

	public int getAsteroidCount() {
		return asteroidCount;
	}

	public void setAsteroidCount(int asteroidCount) {
		this.asteroidCount = asteroidCount;
	}

	public int getFlyingSaucerCount() {
		return flyingSaucerCount;
	}

	public void setFlyingSaucerCount(int flyingSaucerCount) {
		this.flyingSaucerCount = flyingSaucerCount;
	}
	}