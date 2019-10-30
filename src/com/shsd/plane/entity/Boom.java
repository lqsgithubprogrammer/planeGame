package com.shsd.plane.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.shsd.plane.config.Config;

public class Boom {
	public Image image;
	int x, y;
	int w, h;
	public int i;

	public Boom(int x, int y, int w, int h) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.image = new ImageIcon(Config.boomImage).getImage();
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, x, y, w, h, null);
		
	}

	public void move() {
		// TODO Auto-generated method stub
		y = y + 1;
	}
}
