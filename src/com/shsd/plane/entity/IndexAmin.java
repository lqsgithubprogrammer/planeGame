package com.shsd.plane.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import com.shsd.plane.config.Config;

public class IndexAmin {
	public int x,y,w,h,placex,x1,y1,x3,y3,i;
	public float deg;//ÒÆ¶¯½Ç¶È
	public Image bg;
	public AffineTransform form;
	public IndexAmin(int i,int x,int y, int w, int h ,Image bg,float deg) {
		// TODO Auto-generated constructor stub
		this.x= x;
		this.y=y;
		this.placex=x;
		this.w=w;
		this.h=h;
		this.bg= bg;
		this.deg = deg;
		this.i=i;
//		form= new AffineTransform();
//		this.x1=x;
//		this.y1=y;
//		this.x3=x;
//		this.y3=y;
	}
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
//		Graphics2D graphics2d = (Graphics2D) g;
//		form.setToRotation(deg,x,y);
//		graphics2d.drawImage(bg, form,null);
		g.drawImage(bg, x,y,w,h,null);
		
		
	}
	public void run(int y){
		this.x=x+i;
		this.y-=y;
	}
//	public void draw2(Graphics g) {
//		// TODO Auto-generated method stub
//		g.drawImage(bg, x1,y1,w,h,null);
//	}
//	public void draw3(Graphics g) {
//		// TODO Auto-generated method stub
//		g.drawImage(bg, x3,y3,w,h,null);
//	}
//	public void move() {
//		// TODO Auto-generated method stub
//
//		y=y-15;
//
//	}
//	public void move2() {
//		// TODO Auto-generated method stub
//		x1=x1+10;
//		y1=2*Config.frameHeight-(2*Config.frameHeight/Config.frameWidth)*x1;
//	}
//	public void move3() {
//		// TODO Auto-generated method stub
//		x3=x3-10;
//		y3=2*Config.frameHeight/Config.frameWidth*x3+200;
//	}
}
