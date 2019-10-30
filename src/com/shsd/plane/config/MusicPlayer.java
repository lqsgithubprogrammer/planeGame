package com.shsd.plane.config;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	private Clip clip;
	public String path;

	public MusicPlayer(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
		try {
			File file = new File(path);
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(stream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	// ≤•∑≈“Ù¿÷
	public void play() {
		// TODO Auto-generated method stub
		clip.start();
	}

	// ‘›Õ£“Ù¿÷
	public void stop() {
		// TODO Auto-generated method stub
		clip.stop();
	}

	// —≠ª∑≤•∑≈
	public void loop() {
		// TODO Auto-generated method stub
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

}
