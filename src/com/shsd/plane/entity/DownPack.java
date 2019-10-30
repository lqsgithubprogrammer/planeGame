package com.shsd.plane.entity;


import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class DownPack {
	public Image image;
	public int x, y ;
	public int packType ;//掉落包类型
	public int a;//控制功能包2号多久消失
	List<Image> downPacks=new ArrayList<Image>();

	public DownPack(int x, int y, List<Image> downPacks, int type) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.packType = type;
		if(type==1){
			this.image=downPacks.get(0);
			
		}else if (type==2) {
			this.image=downPacks.get(1);
		}else if(type==3){
			this.image=downPacks.get(2);
		}
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, x, y,40,70 ,null);
		y++;
	}
	public void draw2(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, x, y,40,70 ,null);
		y++;
		a++;
	}

}
