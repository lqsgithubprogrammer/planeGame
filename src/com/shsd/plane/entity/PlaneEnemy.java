package com.shsd.plane.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.shsd.plane.config.Config;
import com.shsd.plane.config.MusicPlayer;

public class PlaneEnemy extends JPanel {
	private Image image2;
	public int x, y, x1 = 50, y1 = 50;//x,yΪ�л����� x1,y1Ϊ�л���С
	public boolean isAlive = true;
	public int a2 = 0;// a2���ڿ��Ʊ�ը���������һ��ʱ��
	public boolean everyHurt = false;
	public int enemyType;
	List<Image> enemyImages;
	List<BulletMyPlane> bulletJPlanes;
	public int enemyBlood;
	Random random = new Random();
	int a3;// ���Ƶл�û���ı�ը����

	public PlaneEnemy(int x, int y, int enemyType) {
		// TODO Auto-generated constructor stub
		this.image2 = new ImageIcon(Config.enemyPlane).getImage();
		this.x = x;
		this.y = y;
		this.enemyType = enemyType;
	}

	public PlaneEnemy(int type, int x2, int y2, List<Image> enemyImgs) {
		// TODO Auto-generated constructor stub
		this.x = x2;
		this.y = y2;
		this.enemyType = type;
		this.enemyImages = enemyImgs;

		if (type == 1) {
			this.image2 = new ImageIcon(enemyImgs.get(random.nextInt(2)))
					.getImage();
			this.enemyBlood = 100;
		} else if (type == 2) {
			this.image2 = new ImageIcon(enemyImgs.get(random.nextInt(2) + 2))
					.getImage();
			this.enemyBlood = 200;
		}

	}

	public void draw(Graphics g, List<PlaneEnemy> enemies, MyPlane myPlane,
			List<BulletEnemy> bulletEnemies, List<Image> downPacks,
			List<DownPack> downPackages) {// ����ըЧ���͵л�

		if (this.isAlive == true) {
			g.drawImage(image2, x, y, x1, y1, null);// ���л�

		}
		if (this.isAlive == false) {
			enemies.remove(this);
			new MusicPlayer(Config.enemyBoom).play();
		
			

			if (random.nextInt(30) % 10 == 0) {// �������ս�����ӵ���
				DownPack downPack = new DownPack(x, y, downPacks, 2);
				downPackages.add(downPack);
			}

			if (everyHurt == true) {
				myPlane.blood -= 10;
			}
		}
		if (y > Config.frameHeight / 2 - 100) {// ����һ��ĵл��ٷ�һö�ӵ�
			if (enemyType == 1) {
				BulletEnemy bulletEnemy = new BulletEnemy(enemyType, x + 20,
						y + 20);
				enemyType = 0;
				bulletEnemies.add(bulletEnemy);
			} else if (enemyType == 2) {
				BulletEnemy bulletEnemy = new BulletEnemy(enemyType, x + 20,
						y + 20);
				bulletEnemies.add(bulletEnemy);
				enemyType = 0;
			}
		}
	}

	// TODO Auto-generated method stub

	// // �ж��Ƿ��Ѫ
	// if (this.isAlive == false) {//����
	// this.image2 = new ImageIcon(Config.boomImage).getImage();
	// g.drawImage(image2, x - 50, y - 50, x1 + 100, y1 + 100, null);
	// a2++;
	// } else {
	// if (this.isAttack == false) {// û��Ϯ���Ļ�
	// if (y > Config.frameHeight / 2 - 100) {// ����һ��ĵл��ٷ�һö�ӵ�
	// if (enemyType == 1) {
	// BulletEnemy bulletEnemy = new BulletEnemy(enemyType,
	// x + 20, y + 20);
	// enemyType = 0;
	// bulletEnemies.add(bulletEnemy);
	// } else if (enemyType == 2) {
	// BulletEnemy bulletEnemy = new BulletEnemy(enemyType,
	// x + 20, y + 20);
	// bulletEnemies.add(bulletEnemy);
	// enemyType = 0;
	// }
	// }
	// g.drawImage(image2, x, y, x1, y1, null);// ���л�
	// } else {
	// a3++;
	// }
	// }
	// if (a2 > 5) {// a2���ڿ��Ʊ�ը���������һ��ʱ��
	// enemies.remove(this);
	// if (everyHurt == true) {
	// myPlane.blood -= 20;
	// }
	//
	// }
	// if (a3 > 5) {// a2���ڿ��Ʊ�ը���������һ��ʱ��
	//
	// }
	//
	// }

	public void run() {
		y = y + 1;

	}

	// �Ƿ��ҷ�����
	public void isBoom(List<BulletMyPlane> bulletJPlanes, MyPlane myPlane,
			List<Boom> booms) {
		// TODO Auto-generated method stub
		this.bulletJPlanes = bulletJPlanes;
		if (bulletJPlanes.size() > 0) {
			BulletMyPlane bulletJPlane;
			for (int i = 0; i < bulletJPlanes.size(); i++) {
				bulletJPlane = bulletJPlanes.get(i);
				if (bulletJPlane.x+10 < this.x + 50
						&& bulletJPlane.x+10 > this.x
						&& bulletJPlane.y > this.y
						&& bulletJPlane.y < this.y + 50 && isAlive) {

					if (enemyBlood > 100) {
						this.enemyBlood -= 100;
						bulletJPlanes.remove(bulletJPlane);
						Boom boom = new Boom(x - 20, y - 20, 90, 90);
						booms.add(boom);

					} else if (enemyBlood <= 100) {
						Boom boom = new Boom(x - 20, y - 20, 90, 90);
						booms.add(boom);
						this.isAlive = false;
						if (this.enemyType == 2) {
							myPlane.grade += 3;
						} else if (this.enemyType == 1) {
							myPlane.grade += 1;
						}
					}
					bulletJPlanes.remove(bulletJPlane);
				}

			}

		}
	}

}
