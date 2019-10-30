package com.shsd.plane.entity;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.shsd.plane.UI.GameStartJPanel;
import com.shsd.plane.config.Config;

public class Skills {
	GameStartJPanel gameStartJPanel;
	public static int a = 0;// 用于控制僚机存在时间
	public static int a1 = 0;// 用于控制保护罩存在时间
	public static int a2 = 0;// 用于控制减速存在时间

	public Skills(GameStartJPanel gameStartJPanel) {
		// TODO Auto-generated constructor stub
		this.gameStartJPanel = gameStartJPanel;
	}

	public void enemyMiss() {
		// TODO Auto-generated method stub
		for (int i = 0; i < gameStartJPanel.enemies.size(); i++) {
			PlaneEnemy enemy = gameStartJPanel.enemies.get(i);
			int x = enemy.x;
			int y = enemy.y;
			Boom boom = new Boom(x - 15, y - 20, 90, 90);
			gameStartJPanel.booms.add(boom);

		}
		gameStartJPanel.enemies.removeAll(gameStartJPanel.enemies);
		gameStartJPanel.bulletEnemies.removeAll(gameStartJPanel.bulletEnemies);

		if (gameStartJPanel.boss.bossBlood >= 500) {
			gameStartJPanel.boss.bossBlood -= 500;
		} else {
			gameStartJPanel.boss.bossBlood = 0;
		}
		Boom boom = new Boom(gameStartJPanel.boss.x + 65,
				gameStartJPanel.boss.y + 5, 150, 150);
		gameStartJPanel.booms.add(boom);
		gameStartJPanel.bossBullets.removeAll(gameStartJPanel.bossBullets);
	}

	public static void addMyPlane(GameStartJPanel gameStartJPanel2, Graphics g) {
		// TODO Auto-generated method stub
		if (a < 500) {
			for (int i = 0; i < 5; i++) {// 创僚机子弹和飞机
				if (i == 2) {
					;
				} else {
					if (a % 60 == 0) {
						BulletMyPlane bulletMyPlane = new BulletMyPlane(1,
								gameStartJPanel2.x - 30 + (i - 2) * 55 + 20,
								gameStartJPanel2.y - 10, new ImageIcon(
										Config.bulletEnemy).getImage());

						gameStartJPanel2.bulletJPlanes.add(bulletMyPlane);
					}
					g.drawImage(new ImageIcon(Config.addMyPlane).getImage(),
							gameStartJPanel2.x - 30 + (i - 2) * 55,
							gameStartJPanel2.y, 50, 50, null);

				}
			}
			a++;
		} else {
			gameStartJPanel2.StartSkill1Time=true;
		}

	}

	public static void protectSkill(GameStartJPanel gameStartJPanel2, Graphics g) {// 保护罩
		// TODO Auto-generated method stub

		if (a1 < 600) {
			g.drawImage(new ImageIcon(Config.skill2Play).getImage(),
					gameStartJPanel2.x - 45, gameStartJPanel2.y - 45, 90, 90,
					null);
			gameStartJPanel2.myPlane.isSkill3 = true;
			a1++;
		} else {
			gameStartJPanel2.myPlane.isSkill3 = false;
			gameStartJPanel2.StartSkill2Time=true;
		}
	}

	public static void slowSpeed(GameStartJPanel gameStartJPanel2, Graphics g) {
		// TODO Auto-generated method stub
		if (a2 < 600) {
			g.drawImage(new ImageIcon(Config.slowBg).getImage(), 0, 0,
					Config.frameWidth, Config.frameHeight, null);// 画冰面透明背景
			gameStartJPanel2.speed = 1;
			a2++;

		} else {
			gameStartJPanel2.speed = 5;
			gameStartJPanel2.slowing = false;
			gameStartJPanel2.StartSkill3Time=true;
		}
	}
}
