package com.shsd.plane.UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.shsd.plane.config.Config;
import com.shsd.plane.config.Dal;
import com.shsd.plane.config.MusicPlayer;
import com.shsd.plane.entity.Boom;
import com.shsd.plane.entity.Boss;
import com.shsd.plane.entity.BossBullet;
import com.shsd.plane.entity.BulletEnemy;
import com.shsd.plane.entity.BulletMyPlane;
import com.shsd.plane.entity.DownPack;
import com.shsd.plane.entity.MyPlane;
import com.shsd.plane.entity.PlaneEnemy;
import com.shsd.plane.entity.Skills;

public class GameStartJPanel extends JPanel implements MouseListener,
		KeyListener, ActionListener {

	// public int money;//���
	Image image;
	Image image2;// ������Ϸѡ��͸������ͼ

	BulletMyPlane bulletJPlane;// �һ��ӵ�
	private int bg_y = 0;// ����ͼƬ�ƶ�y����
	public Random random;// �����

	int a1 = 0;// ����û����a1���л��ͷ���һö�ӵ�
	StringBuffer buffer;
	StringBuffer buffer2;
	public MyPlane myPlane;// �һ�
	public Boss boss;// boss
	PopupMenu menu;// �һ��˵�
	MenuItem menuItem1;// ��ʼ��ͣ��ť
	Skills skills;// ����

	// ���¿�ʼ�뷵����ҳ��ť
	JButton button1;// ���¿�ʼ
	JButton button2;// ������ҳ

	private int a = 0;// �߳�ѭ������
	public boolean reStart = true;// ���ò���ʼ��Ϸ��
	public int n = 6;// �����һ����ӵ������Է������ö������
	public int m = 1;// �ؿ�
	public static int myPlanBlood = 200;// �һ�Ѫ����ʼֵ
	int pass_grade;// ���صķ���,����boss��������ʱ����
	public static int buyType = 1;// �����Ļ�һ������ӵ������Ϳ���
	private int bossBlood = 1500;// bossѪ
	int n1;// ���Ƽ���һ����ʱ
	private int n2;// ���Ƽ��ܶ�����ʱ
	private boolean pressSkill1;
	private boolean pressSkill2;
	private boolean pressSkill3;
	private boolean pressSkill4;
	private int n3;// ���Ƽ���������ʱ
	public int speed = 5;// ���ڼ����ĵ��ٶȿ���
	private int n4;// ���Ƽ����ĵ���ʱ
	public boolean slowing;// �Ƿ�Ϊ����״̬
	public int blueNum = 150;// �м�ˢ������
	public int blueInitNum = 150;// ��ʼ������
	private int a2;// ������ʱ���ӿ��ƺ��Զ������ӵ��ļ��
	public boolean StartSkill1Time;// ��ʼ���ܵ���ʱ
	public boolean StartSkill2Time;
	public boolean StartSkill3Time;
	public int myplaneType = 1;

	public int x = Config.frameWidth / 2 - 20, y = Config.frameHeight - 200;// x,y��ʾ�ҷ��ɻ��ķ�λ��
	int x4, y4;// ˢ�µ��ҷ��ɻ�����
	int x1, y1;// x1,y1��ʾ�ӵ��ķ�λ��
	private int x2, y2 = 0;// �з��ɻ���ʼ��λ
	private int x3, y3;// ���ܰ�

	public List<Image> images_bg;// ����
	public List<BulletMyPlane> bulletJPlanes;// �ҷ��ӵ�����
	public List<BulletEnemy> bulletEnemies;// �з��ӵ�����
	public List<Image> downPacks;
	public List<DownPack> downPackages;
	public List<Image> enemyImgs;
	public List<PlaneEnemy> enemies;// �л�����
	public List<BossBullet> bossBullets;// boss�ӵ�����
	public List<Boom> booms;

	public GameStartJPanel() {

		// skills= new Skills(this);

		myPlane = new MyPlane(myPlanBlood, Config.frameWidth / 2 - 20,
				Config.frameHeight - 200);

		boss = new Boss(Config.frameWidth / 2, 0, new ImageIcon(
				Config.bossEnemy).getImage(), bossBlood);

		images_bg = new ArrayList<>();
		images_bg.add(new ImageIcon(Config.startGamebg1).getImage());
		images_bg.add(new ImageIcon(Config.startGamebg2).getImage());
		images_bg.add(new ImageIcon(Config.startGamebg3).getImage());
		images_bg.add(new ImageIcon(Config.startGamebg4).getImage());

		image = images_bg.get(0);
		image2 = new ImageIcon(Config.gameOver).getImage();
		booms = new ArrayList<Boom>();

		enemyImgs = new ArrayList<>();
		enemyImgs.add(new ImageIcon(Config.enemyPlane).getImage());
		enemyImgs.add(new ImageIcon(Config.enemyPlane1).getImage());
		enemyImgs.add(new ImageIcon(Config.enemyPlane2).getImage());
		enemyImgs.add(new ImageIcon(Config.enemyPlane3).getImage());

		button1 = new JButton();
		button1.setLocation(200, 200);
		button1.setSize(50, 50);
		button1.setActionCommand("���¿�ʼ");
		button2 = new JButton();
		button2.setLocation(200, 320);
		button2.setSize(50, 50);
		button2.setActionCommand("������ҳ");

		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button2.setCursor(new Cursor(Cursor.HAND_CURSOR));

		button1.addActionListener(this);
		button2.addActionListener(this);

		enemies = new ArrayList<>();
		bossBullets = new ArrayList<>();
		bulletJPlanes = new ArrayList<>();
		downPackages = new ArrayList<>();
		bulletEnemies = new ArrayList<>();
		downPacks = new ArrayList<>();
		downPacks.add(new ImageIcon(Config.packImg2).getImage());// �ӵ���,type==2
		downPacks.add(new ImageIcon(Config.packImg1).getImage());// ҩ��,type==1
		downPacks.add(new ImageIcon(Config.packImg3).getImage());// ����,type==3

		random = new Random();

		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		// TODO Auto-generated constructor stubs

		menu = new PopupMenu();
		menuItem1 = new MenuItem();
		menuItem1.setLabel("��ͣ��Ϸ");

		MenuItem overItem = new MenuItem("������Ϸ");
		MenuItem restartItem = new MenuItem("���¿�ʼ");

		MenuItem planeItem1 = new MenuItem("�ɻ�����һ");
		MenuItem planeItem2 = new MenuItem("�ɻ����Ͷ�");

		Menu menuBulletType = new Menu("�ӵ�����");
		Menu planeMenu = new Menu("�ɻ�����");

		MenuItem itemBullet1 = new MenuItem("����һ(����)");
		MenuItem itemBullet2 = new MenuItem("���Ͷ�(����ɢ��)");
		MenuItem itemBullet3 = new MenuItem("������(ֱ��ɢ��)");

		planeMenu.add(planeItem1);
		planeMenu.add(planeItem2);

		menuBulletType.add(itemBullet1);
		menuBulletType.add(itemBullet2);
		menuBulletType.add(itemBullet3);

		menu.add(menuItem1);
		menu.add(restartItem);
		menu.add(overItem);
		menu.add(menuBulletType);
		menu.add(planeMenu);

		this.add(menu);

		menuItem1.addActionListener(this);
		itemBullet1.addActionListener(this);
		itemBullet2.addActionListener(this);
		itemBullet3.addActionListener(this);
		overItem.addActionListener(this);
		planeItem1.addActionListener(this);
		planeItem2.addActionListener(this);
		restartItem.addActionListener(this);

		this.addMouseListener(this);
		this.addKeyListener(this);

		// ����
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				if (myPlane.isPass == false || myPlane.gameOver == false) {

					x = e.getX();
					y = e.getY();
				}

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if (myPlane.isPass != true) {

					x = e.getX();
					y = e.getY();
				}
			}
		});

		// GameJFrame.gsp.requestFocus();

	}

	public void startThread() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (reStart) {

					repaint();
					a++;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setFont(Config.font);

		// �����ƶ�����
		bgPaint(g);
		if (myPlane.isPass == false) {

			// �ҷ��ɻ�
			myPlanePaint(g);

			// �����ҷ��ɻ��ӵ�
			myBulletPaint(g);

			// �����л��ӵ��ͻ��Ƶл�
			enemyPlanePaint(g);

			// ���Ƶл��ӵ�
			enemyBulletPaint(g);

			// �����ܰ�
			downPackPaint(g);

			// ������
			skillPaint(g);// �����ܵ���ʱ

			// ���ܹ���
			if (pressSkill1) {
				Skills.addMyPlane(this, g);
			}
			if (pressSkill2) {
				Skills.protectSkill(this, g);

			}
			if (pressSkill3) {
				Skills.slowSpeed(this, g);
			}

			// ��boss�������ӵ�
			bossPaint(g);

			// ��boss�ӵ�
			drawBossBullet(g);

			// ��Ѫ��
			bloodPaint(g);

			// ����ըЧ��
			boomPaint(g);

			// ������
			bluePaint(g);

			// �ж��Ƿ���Ϸ����,���ҷ��ɻ�Ѫ���Ƿ�Ϊ0
			isOver(g);

			myplanePaintAuto();
		} else {
			myPlane.draw2(g);// �ɻ�����
			draw3(g);// �����ַ���
			if (myPlane.y < 0) {
				m++;
				myPlane.isPass = false;
				image = images_bg.get(m % 4);// ����

			}
		}
	}

	private void myplanePaintAuto() {
		// TODO Auto-generated method stub

		if (myplaneType == 1) {

			if (a2 % 30 == 0) {
				new MusicPlayer(Config.shootMyPlane).play();
				BulletMyPlane bulletJPlane = new BulletMyPlane(1, x1, y1,
						new ImageIcon(Config.startGameBullet).getImage());
				bulletJPlanes.add(bulletJPlane);
			}
		} else if (myplaneType == 2) {
			if (a2 % 15 == 0) {
				new MusicPlayer(Config.shootMyPlane).play();
				BulletMyPlane bulletJPlane = new BulletMyPlane(1, x1, y1,
						new ImageIcon(Config.startGameBullet).getImage());
				bulletJPlanes.add(bulletJPlane);
			}
		}
	}

	private void bluePaint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.drawRoundRect(Config.frameWidth - 220, Config.frameHeight - 75, 200,
				15, 10, 10);
		g.setColor(Color.BLUE);
		g.fillRoundRect(Config.frameWidth - 220, Config.frameHeight - 75, 200
				* blueNum / blueInitNum, 15, 10, 10);
		a2++;
		if (a2 % 200 == 0) {
			if (blueNum < blueInitNum) {
				blueNum++;
			}

		}
	}

	// �����ܵ���ʱ
	private void skillPaint(Graphics g) {
		// TODO Auto-generated method stub
		if (blueNum < 10) {
			g.drawImage(new ImageIcon(Config.waitSkill).getImage(), 0,
					Config.frameHeight - 105, 60, 60, null);// q (�Ż�)
		} else {
			g.drawImage(new ImageIcon(Config.skill1).getImage(), 0,
					Config.frameHeight - 105, 60, 60, null);// q (�Ż�)
		}
		if (blueNum < 15) {
			g.drawImage(new ImageIcon(Config.waitSkill).getImage(), 65,
					Config.frameHeight - 105, 60, 60, null);// w(������)

			g.drawImage(new ImageIcon(Config.waitSkill).getImage(), 130,
					Config.frameHeight - 105, 60, 60, null);// e(����)
		} else {
			g.drawImage(new ImageIcon(Config.skill2).getImage(), 65,
					Config.frameHeight - 105, 60, 60, null);// w(������)

			g.drawImage(new ImageIcon(Config.skill3).getImage(), 130,
					Config.frameHeight - 105, 60, 60, null);// e(����)
		}
		if (blueNum < 20) {
			g.drawImage(new ImageIcon(Config.waitSkill).getImage(), 195,
					Config.frameHeight - 105, 60, 60, null);// r(����)
		} else {
			g.drawImage(new ImageIcon(Config.skill4).getImage(), 195,
					Config.frameHeight - 105, 60, 60, null);// r(����)
		}

		if (StartSkill1Time == true)
			startSkill1(g);// q
		if (StartSkill2Time == true)
			startSkill2(g);// w

		if (StartSkill3Time == true)
			startSkill3(g);// e

		if (pressSkill4 == true)
			startSkill4(g);// r

	}

	// ����һ�������ʱ��ʼ
	private void startSkill1(Graphics g) {// �Ż�q
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(n1), 5, Config.frameHeight - 60);
		n1--;
		if (n1 == 0) {
			pressSkill1 = false;
			StartSkill1Time = false;
			Skills.a = 0;
		}
	}

	// ���ܶ�������ʼ��ʱ
	private void startSkill2(Graphics g) {// ������
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(n2), 66, Config.frameHeight - 60);
		n2--;
		if (n2 == 0) {
			Skills.a1 = 0;
			pressSkill2 = false;
			StartSkill2Time = false;
		}
	}

	// ������������ʼ��ʱ2
	private void startSkill3(Graphics g) {// ����
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(n3), 131, Config.frameHeight - 60);
		n3--;
		if (n3 <= 0) {
			pressSkill3 = false;
			Skills.a2 = 0;
			StartSkill3Time = false;
		}
	}

	// �����Ĵ�����ʼ��ʱ
	private void startSkill4(Graphics g) {// r
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(n4), 199, Config.frameHeight - 60);
		n4--;
		if (n4 <= 0) {
			pressSkill4 = false;
		}
	}

	// ����
	private void bgPaint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, 0, bg_y, Config.frameWidth, Config.frameHeight, null);// ����
		g.drawImage(image, 0, bg_y - Config.frameHeight, Config.frameWidth,
				Config.frameHeight, null);
		if (slowing == true) {
			bg_y = bg_y + 2;
		} else {
			bg_y = bg_y + m + 2;
		}
		if (bg_y > Config.frameHeight) {
			bg_y = 0;
		}
	}

	// �ҷ��ɻ�
	private void myPlanePaint(Graphics g) {
		// TODO Auto-generated method stub
		// x4 = x;
		// y4 = y;
		myPlane.boom(this);
		// myPlane.move(x4, y4);
		myPlane.draw(g, x, y);
		x1 = x - 10;
		y1 = y - 35;
		if (myPlane.grade >= pass_grade + 30 + 60 * (m - 1)) {
			myPlane.showBoss = true;
		}
		if (myPlane.blood <= 0) {
			myPlane.gameOver = true;
		}

	}

	// ���ҷ��ɻ��ӵ�
	private void myBulletPaint(Graphics g) {
		// TODO Auto-generated method stub

		if (bulletJPlanes.size() > 0) {
			for (int i = 0; i < bulletJPlanes.size(); i++) {
				bulletJPlane = bulletJPlanes.get(i);
				bulletJPlane.draw(g);
				bulletJPlane.run(15);
				if (bulletJPlane.y < 0) {
					bulletJPlanes.remove(bulletJPlane);
				}

			}
		}
	}

	// ���л� ���л����ӵ�
	private void enemyPlanePaint(Graphics g) {
		// TODO Auto-generated method stub

		if (a % (50 - speed * m) == 0) {// �����л����ӵ�
			// this.x2 = 100 * random.nextInt(10);
			this.x2 = random.nextInt(Config.frameWidth);
			PlaneEnemy enemy = null;
			if (m == 1) {
				enemy = new PlaneEnemy(x2, y2, 1);
				enemies.add(enemy);
			} else if (m >= 2) {
				enemy = new PlaneEnemy(random.nextInt(2) + 1, x2, y2, enemyImgs);
				enemies.add(enemy);
			}
			a1++;
			if (a1 % 2 == 0) {// �����л��ӵ�
				if (m == 1) {
					BulletEnemy bulletEnemy = new BulletEnemy(1, enemy.x + 20,
							enemy.y + 20);
					bulletEnemies.add(bulletEnemy);
				} else if (m >= 2) {
					if (enemy.enemyType == 1) {
						BulletEnemy bulletEnemy = new BulletEnemy(
								enemy.enemyType, enemy.x + 20, enemy.y + 20);
						bulletEnemies.add(bulletEnemy);
					} else if (enemy.enemyType == 2) {
						BulletEnemy bulletEnemy = new BulletEnemy(
								enemy.enemyType, enemy.x + 20, enemy.y + 20);
						bulletEnemies.add(bulletEnemy);

					}
				}
			}
		}
		// ���л�
		if (enemies.size() > 0) {
			for (int i = 0; i < enemies.size(); i++) {
				PlaneEnemy enemy = enemies.get(i);
				enemy.draw(g, enemies, myPlane, bulletEnemies, downPacks,
						downPackages);
				enemy.run();
				if (enemy.x > Config.frameWidth || enemy.y > Config.frameHeight
						|| enemy.a2 > 15) {
					enemies.remove(enemy);
				}
				enemy.isBoom(bulletJPlanes, myPlane, booms);

			}
		}
	}

	// ���л��ӵ�
	private void enemyBulletPaint(Graphics g) {
		// TODO Auto-generated method stub
		if (bulletEnemies.size() > 0) {
			for (int i = 0; i < bulletEnemies.size(); i++) {
				BulletEnemy enemy = bulletEnemies.get(i);
				enemy.draw(g);
				enemy.run();
				if (enemy.x > Config.frameWidth || enemy.y > Config.frameHeight) {
					bulletEnemies.remove(enemy);
				}
			}
		}
	}

	boolean changePack = true;

	// �����ܰ�
	private void downPackPaint(Graphics g) {
		// TODO Auto-generated method stubs
		// �������ܰ�
		if (a % 1000 == 0) {
			// ����ҩ��
			if (changePack) {
				x3 = random.nextInt(Config.frameWidth - 30);
				DownPack package1 = new DownPack(x3, y3, downPacks, 1);
				downPackages.add(package1);
				if (m >= 4) {
					changePack = false;
				}
			} else if (!changePack) {
				if (m >= 4) {// ��������
					x3 = random.nextInt(Config.frameWidth - 30);
					DownPack package2 = new DownPack(x3, y3, downPacks, 3);
					downPackages.add(package2);
					changePack = true;
				}
			}

		}

		if (downPackages.size() > 0) {
			for (int i = 0; i < downPackages.size(); i++) {
				DownPack downPackage = downPackages.get(i);
				if (downPackage.packType == 1 || downPackage.packType == 3) {
					downPackage.draw(g);
				} else if (downPackage.packType == 2) {
					downPackage.draw2(g);
				}
				if (downPackage.a > 500) {
					downPackages.remove(downPackage);
				}
				if (downPackage.x > Config.frameWidth
						|| downPackage.y > Config.frameHeight) {
					downPackages.remove(downPackages);
				}
			}
		}
	}

	// ��boss
	private void bossPaint(Graphics g) {
		// TODO Auto-generated method stub

		if (myPlane.showBoss == true) {
			a = 1;
			boss.isboom(bulletJPlanes, myPlane, booms);
			boss.bossPaint(g);
			boss.move();

			boss.createBullete(g, bossBullets, this);

		}
		if (boss.bossBlood <= 0) {
			new MusicPlayer(Config.bossBoom).play();
			new MusicPlayer(Config.enemyBoom).play();
			myPlane.grade += 20;
			myPlane.isPass = true;
			myPlane.showBoss = false;
			x = Config.frameWidth / 2 - 20;
			y = Config.frameHeight - 200;

			bossBlood += 500;
			boss.bossBlood = bossBlood;
			boss.n = 0;// �ֿ��Ի�boss����
			boss.x = Config.frameWidth / 2 - 122;
			boss.y = 0;
			boss.a = 0;

			pass_grade = myPlane.grade;// ��¼����
			a2 = 0;
			// ��ռ���
			enemies.removeAll(enemies);
			booms.removeAll(booms);
			bulletEnemies.removeAll(bulletEnemies);
			bulletJPlanes.removeAll(bulletJPlanes);
			bossBullets.removeAll(bossBullets);

		}
	}

	// ��boss�ӵ�
	private void drawBossBullet(Graphics g) {
		// TODO Auto-generated method stub
		if (bossBullets.size() > 0) {
			for (int i = 0; i < bossBullets.size(); i++) {
				BossBullet bossBullet = bossBullets.get(i);

				if (bossBullet.type == 3) {// ����
					bossBullet.move(boss.x + 95);
					bossBullet.draw2(g);
				} else {// �����ӵ�
					bossBullet.move();
					bossBullet.draw(g);
				}

				if (bossBullet.x > Config.frameWidth
						|| bossBullet.y > Config.frameHeight) {
					bossBullets.remove(bossBullet);
				}
			}
		}
	}

	// ��Ѫ����÷�
	private void bloodPaint(Graphics g) {
		// TODO Auto-generated method stub
		g.setFont(Config.font);
		g.setColor(Color.WHITE);
		buffer = new StringBuffer("Ѫ��ʣ��:  " + myPlane.blood);
		buffer2 = new StringBuffer("��ǰ�÷�:  " + myPlane.grade);

		g.setColor(Color.WHITE);

		g.drawString(buffer.toString(), Config.frameWidth - 300, 80);
		g.drawString(buffer2.toString(), Config.frameWidth - 300, 130);
		g.drawString("��ǰ�ؿ�����" + String.valueOf(m), Config.frameWidth - 300, 180);

		g.setFont(new Font("", Font.BOLD, 13));
		g.drawString("�ҷ�Ѫ��:", 0, 15);
		g.setColor(Color.red);
		g.drawRect(60, 5, Config.frameWidth - 60, 10);
		g.fillRect(60, 5, (Config.frameWidth - 60) * myPlane.blood
				/ myPlanBlood, 10);

		if (myPlane.showBoss == true && boss.bossBlood > 0) {
			g.setColor(Color.WHITE);
			g.drawString("BOSSѪ��:", 0, 50);
			g.setColor(Color.red);
			g.drawRect(70, 40, Config.frameWidth - 80, 10);
			g.fillRect(70, 40, (Config.frameWidth - 80) * boss.bossBlood
					/ bossBlood, 10);
		}

	}

	// ����ը
	private void boomPaint(Graphics g) {
		// TODO Auto-generated method stub
		if (booms.size() > 0) {
			for (int i = 0; i < booms.size(); i++) {
				Boom boom = booms.get(i);
				if (boom.i < 15) {
					boom.i++;
					boom.draw(g);
					boom.move();
				} else {
					booms.remove(boom);
				}
			}
		}
	}

	// �ж���Ϸ����
	private void isOver(Graphics g) {
		// TODO Auto-generated method stub
		if (myPlane.gameOver == true) {
			stopThread();
			

			
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			this.add(button1);
			this.add(button2);
//			button1.setEnabled(true);
//			button2.setEnabled(true);

			draw2(g);// ��������Ŀ�������밴ť���ƣ�

		}
	}

	// �������ַ���
	public void draw2(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image2, 0, 0, this);

		g.setColor(new Color(233, 230, 234));
		g.fillOval(190, 190, 70, 70);
		g.fillOval(190, 313, 70, 70);
		g.setColor(new Color(148, 158, 158));
		g.drawString("���¿�ʼ", 195, 227);
		g.drawString("������ҳ", 195, 352);

		g.setFont(Config.font);
		g.setColor(Color.WHITE);
		g.drawString("��Ϸ��������", 150, 450);

	}

	// ��ʤ���ַ���
	public void draw3(Graphics g) {
		// TODO Auto-generated method stub

		g.setColor(Color.WHITE);
		g.setFont(Config.font);
		g.drawString("��Ϸ����!������������һ��......", 20, 350);
	}

	/**
	 * 
	 * ��Ļ����������¼�����
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this) {

			// if (buyType == 1) {
			// BulletMyPlane bulletJPlane = new BulletMyPlane(1, x1, y1,
			// new ImageIcon(Config.startGameBullet).getImage());
			// bulletJPlanes.add(bulletJPlane);
			// } else
			if (buyType == 2) {
				new MusicPlayer(Config.shootMyPlane).play();
				for (int i = 0; i < 10; i++) {
					BulletMyPlane bulletMyPlane = new BulletMyPlane(2,
							(i - 5) * 2, x1, y1, new ImageIcon(
									Config.startGameBullet).getImage());
					bulletJPlanes.add(bulletMyPlane);
				}
			} else if (buyType == 3) {
				new MusicPlayer(Config.shootMyPlane).play();
				for (int i = 0; i < 4; i++) {
					BulletMyPlane bulletMyPlane = new BulletMyPlane(
							(i - 2) * 20, x1, y1, new ImageIcon(
									Config.startGameBullet).getImage(), 3);
					bulletJPlanes.add(bulletMyPlane);
				}
			}

			if (n <= 5) {// �������5�������ӵ��ͻظ�Ϊ��ͨ�ӵ�
				n++;
				for (int i = 0; i < 10; i++) {
					BulletMyPlane bulletMyPlane = new BulletMyPlane(2,
							(i - 5) * 2, x1, y1, new ImageIcon(
									Config.startGameBullet).getImage());
					bulletJPlanes.add(bulletMyPlane);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int mods = e.getModifiers();
		if ((mods & InputEvent.BUTTON3_MASK) != 0) {
			menu.show(this, e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// ���̼����¼�����
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if (c == 'w' && y > 0) {
			y = y - 8;
		}
		if (c == 'a' && x > 0) {
			x = x - 8;
		}
		if (c == 's' && y < Config.frameHeight - 20) {
			y = y + 8;
		}
		if (c == 'd' && x < Config.frameWidth) {
			x = x + 8;
		}
		if (c == 'j') {
			for (int i = 0; i < 10; i++) {
				BulletMyPlane bulletMyPlane = new BulletMyPlane(2, (i - 5) * 2,
						x1, y1,
						new ImageIcon(Config.startGameBullet).getImage());
				bulletJPlanes.add(bulletMyPlane);
			}

		}
		if (c == 'q') {
			if (pressSkill1 == false && blueNum >= 10) {
				n1 = 600;
				pressSkill1 = true;
				blueNum -= 10;
				// skills = new Skills(this);
				// skills.enemyMiss();
			}
		}
		if (c == 'w') {
			if (pressSkill2 == false && blueNum >= 15) {
				n2 = 700;
				pressSkill2 = true;
				blueNum -= 15;
			}
		}
		if (c == 'e') {
			if (pressSkill3 == false && blueNum >= 15) {
				n3 = 800;
				pressSkill3 = true;
				slowing = true;
				blueNum -= 20;
			}
		}
		if (c == 'r') {
			if (pressSkill4 == false && blueNum >= 20) {
				n4 = 1000;
				new Skills(this).enemyMiss();
				pressSkill4 = true;
				blueNum -= 20;

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	// ������Ϸ

	public void stopThread() {
		// TODO Auto-generated method stub
		this.reStart = false;

	}

	// ��ʼ��Ϸ
	private void startGame() {
		// TODO Auto-generated method stub
		reStart = true;
		startThread();
	}

	// ���ò���ʼ�߳�
	public void reStart() {
		// TODO Auto-generated method stub
		// ����
		enemies.removeAll(enemies);
		booms.removeAll(booms);
		bulletEnemies.removeAll(bulletEnemies);
		bulletJPlanes.removeAll(bulletEnemies);
		downPackages.removeAll(downPackages);
		bossBullets.removeAll(bossBullets);

		// �һ�
		x = Config.frameWidth / 2 - 20;
		y = Config.frameHeight - 200;
		myPlane.grade = 0;
		myPlane.isPass = false;
		myPlane.gameOver = false;
		myPlane.showBoss = false;
		myPlane.blood = myPlanBlood;

		// boss
		bossBlood=1500;
		boss.bossBlood = bossBlood;
		boss.n = 0;// �ֿ��Ի�boss����
		boss.x = Config.frameWidth / 2 - 122;
		boss.y = 0;
		boss.a = 0;

		pass_grade = 0;

		// ����
		m = 1;

		a = 0;
		a1 = 0;
		a2 = 0;
		image = images_bg.get(0);
		blueNum = blueInitNum;

		reStart = true;
		startThread();
		this.requestFocus();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		if (str.equals("��ͣ��Ϸ")) {
			this.reStart = false;
			menuItem1.setLabel("��ʼ��Ϸ");
		}
		if (str.equals("��ʼ��Ϸ")) {
			startGame();
			menuItem1.setLabel("��ͣ��Ϸ");
		}
		if (str.equals("����һ(����)")) {
			buyType = 1;
		}
		if (str.equals("���Ͷ�(����ɢ��)")) {
			buyType = 2;
		}
		if (str.equals("������(ֱ��ɢ��)")) {
			buyType = 3;
		}
		if (str.equals("������Ϸ")) {
			myPlane.gameOver = true;
		}
		if (str.equals("�ɻ�����һ")) {
			myPlane.image = new ImageIcon(Config.startGameimgMy).getImage();
			myplaneType = 1;
		}
		if (str.equals("�ɻ����Ͷ�")) {
			myPlane.image = new ImageIcon(Config.myPlaneImg2).getImage();
			myplaneType = 2;
		}
		if (e.getSource() == button1) {
			if (str.equals("���¿�ʼ")) {
				this.remove(button1);
				this.remove(button2);
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
				reStart();
				// д����
				if (myPlane.gameOver==true) {
					Dal dal = new Dal();
					try {
						dal.addscores(1, String.valueOf(myPlane.grade));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		} else {
			if (str.equals("���¿�ʼ")) {
				myPlane.gameOver = true;
			}
		}
		if (str.equals("������ҳ")) {
			this.remove(button1);
			this.remove(button2);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			stopThread();
			if (myPlane.gameOver==true) {
				Dal dal = new Dal();
				try {
					dal.addscores(1, String.valueOf(myPlane.grade));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_welcome");
		}

	}
}
