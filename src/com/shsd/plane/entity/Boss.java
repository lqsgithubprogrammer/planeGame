package com.shsd.plane.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.shsd.plane.UI.GameStartJPanel;
import com.shsd.plane.config.Config;
import com.shsd.plane.config.MusicPlayer;

public class Boss {
	public int x;
	public int y;
	Image image;
	public int a;// 循环次数
	public int n;// 画boss大招
	public int bossBlood;
	int y1 = (Config.frameHeight - 20) * 1 / 2 - 200;
	int y2 = (Config.frameHeight - 20) * 1 / 6 - 200;

	public Boss(int x, int y, Image image, int blood) {
		// TODO Auto-generated constructor stub
		this.x = x - 122;
		this.y = y;
		this.image = image;
		this.bossBlood = blood;
	}

	public void bossPaint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, x, y, 244, 157, null);
		a++;

	}

	public void move() {
		// TODO Auto-generated method stub
		if (y < y2) {
			y++;
		}
		if (y >= y2 && x <= Config.frameWidth / 2 - 122 && y < y1) {
			y += 1;
			x = x - 2 * Config.frameWidth / Config.frameHeight;
			if (x < 0)
				x = 0;
		}
		if (y >= y1) {
			if (x <= Config.frameWidth - 250) {
				x++;
			} else {
				x = x - 1;
				y = y - 1;
			}
		}
		if (x >= Config.frameWidth / 2 - 122 && y < y1 && y > y2) {

			y = y - 1;
			x = x - 3 * Config.frameWidth / Config.frameHeight;
			if (x <= Config.frameWidth / 2 - 122) {
				x = Config.frameWidth / 2 - 122;
			}
		}
	}

	public void createBullete(Graphics g, List<BossBullet> bossBullets,
			GameStartJPanel gameStartJPanel) {
		// TODO Auto-generated method stub

		// 创建子弹
		if (a % (600 - gameStartJPanel.speed * (gameStartJPanel.m * 8)) == 0) {
			for (int i = 0; i < 5; i++) {
				BossBullet bossBullet = new BossBullet((i - 2), x + 122,
						y + 150, new ImageIcon(Config.bossBullet1).getImage());
				bossBullets.add(bossBullet);
			}
		}

		if (bossBlood < 800) {
			if (a % (100 - gameStartJPanel.speed * (gameStartJPanel.m + 1)) == 0) {
				BossBullet bossBullet2 = new BossBullet(x + 122, y + 150,
						new ImageIcon(Config.bossBullet2).getImage());
				bossBullets.add(bossBullet2);
			}
		}
		if (gameStartJPanel.m >= 3) {
			if (bossBlood > 800 && bossBlood < 1500) {
				if (n < 500) {
					if (n % 9 == 0) {
						BossBullet bossBullet3 = new BossBullet(x + 122,
								y + 135,
								new ImageIcon(Config.bossBullet3).getImage(), 3);
						bossBullets.add(bossBullet3);
					}
					n++;
				}
			}

			if (bossBlood > 400 && bossBlood < 800) {
				if (n < 1000) {
					if (n % 9 == 0) {
						BossBullet bossBullet3 = new BossBullet(x + 122,
								y + 135,
								new ImageIcon(Config.bossBullet3).getImage(), 3);
						bossBullets.add(bossBullet3);
					}
					n++;
				}
			}
			if (bossBlood < 400) {
				if (n < 1500) {
					if (n % 9 == 0) {
						BossBullet bossBullet3 = new BossBullet(x + 122,
								y + 135,
								new ImageIcon(Config.bossBullet3).getImage(), 3);
						bossBullets.add(bossBullet3);
					}
					n++;
				}
			}
		} 
		else {
			if (bossBlood < 400) {
				n++;
				if (n < 200) {
					if (n % 15 == 0) {
						g.setFont(new Font("", Font.BOLD, 50));
						g.setColor(Color.RED);
						g.drawString("请注意!!!请注意!!!", 20, 400);
					}
				}
				if (200 < n && n <= 700) {

					BossBullet bossBullet3 = new BossBullet(x + 122, y + 135,
							new ImageIcon(Config.bossBullet3).getImage(), 3);
					bossBullets.add(bossBullet3);
				}
			}
		}
	}

	public void isboom(List<BulletMyPlane> bulletJPlanes, MyPlane myPlane,
			List<Boom> booms) {
		// TODO Auto-generated method stub
		if (bulletJPlanes.size() > 0) {// 与我机子弹相撞
			for (int i = 0; i < bulletJPlanes.size(); i++) {
				BulletMyPlane bulletMyPlane = bulletJPlanes.get(i);
				if (bulletMyPlane.y < this.y + 157
						&& bulletMyPlane.x + 10 > this.x
						&& bulletMyPlane.x + 10 < this.x + 244) {
					Boom boom = new Boom(x + 47, y + 5, 150, 150);
					booms.add(boom);
					if (bossBlood > 0)
						new MusicPlayer(Config.boosShoot).play();
					bossBlood -= 10;
					bulletJPlanes.remove(bulletMyPlane);
				}
			}
		}
	}
}
