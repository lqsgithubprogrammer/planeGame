package com.shsd.plane.entity;


import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.shsd.plane.config.Config;

public class BossBullet {
	public int x,y,i,i2;
	Image image;
	public int type;
	public BossBullet(int i, int x1, int y1, Image image2) {
		// TODO Auto-generated constructor stub
		this.x=x1;
		this.y=y1;
		this.image=image2;
		this.i=i;
	}
	public BossBullet(int x2, int y2, Image image2) {
		// TODO Auto-generated constructor stub
		this.x=x2;
		this.y=y2;
		this.image=image2;
	}
	public BossBullet(int x3, int y3, Image image2, int type) {
		// TODO Auto-generated constructor stub
		this.x=x3;
		this.y=y3;
		this.image=image2;
		this.type = type;
	}
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image,x,y,null);
	}
	public void draw2(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image,x,y,50,60,null);
	}
	public  void move() {
		// TODO Auto-generated method stub
		x=x+i;
		y=y+3;
		
	}
	public void move(int x2) {
		// TODO Auto-generated method stub
		y=y+3;
		x=x2;
	}
	
	
}
