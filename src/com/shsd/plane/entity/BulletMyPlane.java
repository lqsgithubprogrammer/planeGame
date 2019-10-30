package com.shsd.plane.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.shsd.plane.UI.GameStartJPanel;
import com.shsd.plane.config.Config;

public class BulletMyPlane extends JPanel {
	public Image image;
	public int x, y,i,type;
//	public BulletMyPlane(int i,int x ,int y,Image image) {
//		// TODO Auto-generated constructor stub
//		this.image = image;
//		this.x=x; this.y=y;this.i=i;
//	}
	public BulletMyPlane(int type ,int x,int y, Image image) {//子弹类型1普通单弹
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.image=image;
		this.type=type;
	}
	public BulletMyPlane(int type, int i, int x2, int y2, Image image2) {//子弹类型2
		// TODO Auto-generated constructor stub
		this.x=x2;
		this.y=y2;
		this.image=image2;
		this.i=i;
		this.type= type;
	}
	public BulletMyPlane(int i, int x1, int y1, Image image2, int type) {//子弹类型3
		// TODO Auto-generated constructor stub
		this.x=x1+i;
		this.y=y1;
		this.image=image2;
		this.type= type;
	}
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(image,x,y,null);
	}
	
	public void run(int y){
		if(this.type==2){
			this.x=x+i;
			this.y-=y;
		}else if(type==1 || type ==3){
			this.y-=y;
		}
		
	}
}
