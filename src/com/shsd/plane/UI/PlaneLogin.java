package com.shsd.plane.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.shsd.plane.config.Config;

public class PlaneLogin extends JPanel {
	private Image image;
	private Image image2;
	private Image image3;
	private int x = 20, y = 20;//动画飞机
	public boolean reStart = true;// 控制线程

	public PlaneLogin() {
		// TODO Auto-generated constructor stub
		this.image = new ImageIcon(Config.loginGameimage1).getImage();
		this.image2 = new ImageIcon(Config.loginGamebg).getImage();
		this.image3 = new ImageIcon(Config.loginGameimage2).getImage();

	}

	public void startThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				// TODO Auto-generated method stub
				while (reStart) {

					repaint();
					try {
						x = x + 15;
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
		super.paintComponent(g);
		g.drawImage(image2, 0, 0, null);
		//动画飞机
		g.drawImage(image, x, y, null);
		g.drawImage(image, x, y + 100, null);
		g.drawImage(image, x, y + 200, null);
		//动画子弹
		g.drawImage(image3, x - 100, y, null);
		g.drawImage(image3, x - 100, y + 100, null);
		g.drawImage(image3, x - 100, y + 200, null);

		g.setFont(Config.font);
		g.setColor(Color.red);
		if (x / 5 % 4 == 0) {
			g.drawString("游戏加载中,请稍后......", 80, 350);
		}
		if (x > Config.frameWidth + 100) {
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_start");
			this.stopThread();
			GameJFrame.gsp.requestFocus();
			GameJFrame.gsp.reStart();
		
		}

	}

	// 停止线程
	public void stopThread() {
		// TODO Auto-generated method stub
		this.reStart=false;
	}
	//重置并开始线程
	public void restart() {
		// TODO Auto-generated method stub
			x = 20;
			y = 20;
			this.reStart=true;
			startThread();

	}
}
