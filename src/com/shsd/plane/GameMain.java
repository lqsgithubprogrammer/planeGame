package com.shsd.plane;

import com.shsd.plane.UI.GameJFrame;
import com.shsd.plane.config.Config;
import com.shsd.plane.config.MusicPlayer;
/**
 * 
 * @author Administrator
 *
 */
public class GameMain {
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		GameJFrame gameJFrame = new GameJFrame("youxi");
		MusicPlayer musicPlayer = new MusicPlayer(Config.welcomeMu);
		musicPlayer.loop();
		musicPlayer.play();
	}
}
