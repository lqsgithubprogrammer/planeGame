package com.shsd.plane.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import com.shsd.plane.UI.GameStartJPanel;
import com.shsd.plane.config.Config;

public class MyPlane {
	public Image image;
	public int x, y;
	public int blood;// 飞机血量
	public int grade = 0;
	public boolean gameOver = false;
	public boolean showBoss, isPass;
	Graphics g;
	int x_c, y_c;// 鼠标位置距离我机位置的差
	public boolean isSkill3;

	public MyPlane(int myPlanBlood, int x2, int y2) {
		// TODO Auto-generated constructor stub
		this.image = new ImageIcon(Config.startGameimgMy).getImage();
		this.blood = myPlanBlood;
		this.x = x2;
		this.y = y2;
	}

	public void draw(Graphics g, int x2, int y2) {
		// TODO Auto-generated method stub
		g.drawImage(image, x, y, 65, 70, null);
		this.x = x2 - 32;
		this.y = y2 - 35;
		

		// if (this.grade >= 5000) {
		// GameStartJPanel.gameOver=true;
		// }
	}

	public void draw2(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, x, y, 65, 70, null);
		y -= 4;
	}

	public void boom(GameStartJPanel gameStartJPanel) {// 被普通敌机子弹伤害
		// TODO Auto-generated method stub
		if (gameStartJPanel.bulletEnemies.size() > 0) {
			for (int i = 0; i < gameStartJPanel.bulletEnemies.size(); i++) {
				BulletEnemy enemy = gameStartJPanel.bulletEnemies.get(i);
				if (enemy.y > this.y && enemy.y < this.y + 70
						&& enemy.x > this.x && enemy.x < this.x + 65) {
					Boom boom = new Boom(x - 20, y - 20, 90, 90);
					gameStartJPanel.booms.add(boom);
					if (blood > 0) {
						if (isSkill3 == false) {
							if (enemy.enemyBulletType == 1) {
								blood -= 10;
							} else if (enemy.enemyBulletType == 2
									&& isSkill3 == false) {
								blood -= 20;
								if (blood < 0) {
									blood = 0;
								}
							}
						}
						gameStartJPanel.bulletEnemies.remove(enemy);
					}
				}
			}
		}

		if (gameStartJPanel.enemies.size() > 0) {// 与敌机相撞
			for (int i = 0; i < gameStartJPanel.enemies.size(); i++) {
				PlaneEnemy enemy = gameStartJPanel.enemies.get(i);
				if (enemy.y + 25 > this.y && enemy.y + 25 < this.y + 70
						&& enemy.x + 25 > this.x && enemy.x + 25 < this.x + 65) {
					Boom boom = new Boom(x - 20, y - 20, 90, 90);
					gameStartJPanel.booms.add(boom);
					enemy.isAlive = false;
					if (isSkill3 == false) {
						enemy.everyHurt = true;
					}

				}
			}
		}
		if (gameStartJPanel.downPackages.size() > 0) {// 捡到 掉落包
			for (int i = 0; i < gameStartJPanel.downPackages.size(); i++) {
				DownPack pack = gameStartJPanel.downPackages.get(i);
				if (pack.y + 70 > this.y && pack.y + 70 < this.y + 70
						&& pack.x + 20 > this.x && pack.x + 20 < this.x + 65) {
					if (pack.packType == 1) {// 为药包
						if (blood <= GameStartJPanel.myPlanBlood - 50) {
							this.blood += 50;
						} else if (blood > GameStartJPanel.myPlanBlood - 50
								&& blood <= GameStartJPanel.myPlanBlood) {
							blood = GameStartJPanel.myPlanBlood;
						}
					} else if (pack.packType == 2) {// 为弹包
						gameStartJPanel.n = 0;
					} else if (pack.packType == 3) {// 蓝包
						if (gameStartJPanel.blueNum <= gameStartJPanel.blueInitNum - 20) {
							gameStartJPanel.blueNum += 20;
						} else {
							gameStartJPanel.blueNum = gameStartJPanel.blueInitNum;
						}

					}

					gameStartJPanel.downPackages.remove(pack);

				}
			}
		}
		
		
		if (gameStartJPanel.bossBullets.size() > 0) {// 与boss子弹相撞
			for (int i = 0; i < gameStartJPanel.bossBullets.size(); i++) {
				BossBullet bossBullet = gameStartJPanel.bossBullets.get(i);
				if (bossBullet.y + 65 > this.y
						&& bossBullet.y + 65 < this.y + 70
						&& bossBullet.x + 10 > this.x
						&& bossBullet.x + 10 < this.x + 65) {

					if (blood > 0 && isSkill3 == false)
						this.blood -= 10;
					Boom boom = new Boom(x - 20, y - 20, 90, 90);
					gameStartJPanel.booms.add(boom);
					gameStartJPanel.bossBullets.remove(bossBullet);
				}
			}
		}

		// 与boss相撞
		if (showBoss == true) {
			if (y + 35 < gameStartJPanel.boss.y + 157
					&& y + 35 > gameStartJPanel.boss.y
					&& x + 32 > gameStartJPanel.boss.x
					&& x + 32 < gameStartJPanel.boss.x + 244) {
				Boom boom = new Boom(x - 20, y - 20, 90, 90);
				gameStartJPanel.booms.add(boom);
				if (blood > 0 && isSkill3 == false)
					this.blood -= 10;
			}
		}
	}

	public void move(int x1, int y1) {// 用于延迟移动的
		// TODO Auto-generated method stub
		x_c = x1 - this.x;
		y_c = y1 - this.y;
		if (x_c < 0) {
			this.x -= 2;
		} else if (x_c > 0) {
			this.x += 2;
		}
		if (y_c < 0) {
			y -= 2;
		} else if (y_c > 0) {
			y += 3;
		}

	}
}
