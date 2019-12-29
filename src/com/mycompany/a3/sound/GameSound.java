package com.mycompany.a3.sound;

public class GameSound {
	private Sound fireMissile;
	private Sound missileHit;
	private Sound gameOver;
	private Sound reload;
	private BGSound background;
	private boolean enable;
	
	
	public GameSound() {
		fireMissile = new Sound("Rocket.WAV");
		missileHit = new Sound("Explosion.WAV");
		gameOver = new Sound("End.wav");
		background = new BGSound("Music.wav");
		reload = new Sound("Reload.wav");
		enable = true;
	}
	
	public void fireMissileSound(int volume) {
		fireMissile.play(volume);
	}
	
	public void Reload(int volume) {
		reload.play(volume);
	}
	
	public void MissileHitSound(int volume) {
		missileHit.play(volume);
	}
	
	public void GameOverSound(int volume) {
		gameOver.play(volume);
	}
	
	public void playMusic(int volume) { //play bg sound
		if(enable){
			background.play(volume);
		}
	}
	
	public void pauseMusic() { //pause
		background.pause();
	}
}
