package com.shsd.plane.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.shsd.plane.config.Config;

public class BulletEnemy {
	private Image image;
	public int x, y;
	int enemyBulletType;

	public BulletEnemy(int enemyBulletType,int x, int y ) {
		// TODO Auto-generated constructor stub
		this.image = new ImageIcon(Config.bulletEnemy).getImage();
		this.x = x;
		this.y = y;
		this.enemyBulletType = enemyBulletType;
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if (enemyBulletType == 1) {
			g.drawImage(image, x, y, null);
		} else if (enemyBulletType == 2) {
			this.image=new ImageIcon(Config.bossBullet1).getImage();
			g.drawImage(image, x-10, y, null);
			g.drawImage(image, x+10, y, null);
		}
	}

	public void run() {
		y = y + 3;

	}
}
