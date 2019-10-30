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

	// public int money;//金币
	Image image;
	Image image2;// 结束游戏选项透明背景图

	BulletMyPlane bulletJPlane;// 我机子弹
	private int bg_y = 0;// 背景图片移动y坐标
	public Random random;// 随机数

	int a1 = 0;// 控制没制作a1个敌机就发射一枚子弹
	StringBuffer buffer;
	StringBuffer buffer2;
	public MyPlane myPlane;// 我机
	public Boss boss;// boss
	PopupMenu menu;// 右击菜单
	MenuItem menuItem1;// 开始暂停按钮
	Skills skills;// 技能

	// 重新开始与返回首页按钮
	JButton button1;// 重新开始
	JButton button2;// 返回首页

	private int a = 0;// 线程循环次数
	public boolean reStart = true;// 重置并开始游戏吗
	public int n = 6;// 控制我机捡到子弹包可以发射多少枚超级弹
	public int m = 1;// 关卡
	public static int myPlanBlood = 200;// 我机血量初始值
	int pass_grade;// 过关的分数,仅在boss机被击毁时更新
	public static int buyType = 1;// 点击屏幕我机发射子弹的类型控制
	private int bossBlood = 1500;// boss血
	int n1;// 控制技能一倒计时
	private int n2;// 控制技能二倒计时
	private boolean pressSkill1;
	private boolean pressSkill2;
	private boolean pressSkill3;
	private boolean pressSkill4;
	private int n3;// 控制技能三倒计时
	public int speed = 5;// 用于技能四的速度控制
	private int n4;// 控制技能四倒计时
	public boolean slowing;// 是否为减速状态
	public int blueNum = 150;// 中间刷新蓝条
	public int blueInitNum = 150;// 初始化蓝条
	private int a2;// 蓝条定时增加控制和自动发射子弹的间隔
	public boolean StartSkill1Time;// 开始技能倒计时
	public boolean StartSkill2Time;
	public boolean StartSkill3Time;
	public int myplaneType = 1;

	public int x = Config.frameWidth / 2 - 20, y = Config.frameHeight - 200;// x,y表示我方飞机的方位；
	int x4, y4;// 刷新的我方飞机坐标
	int x1, y1;// x1,y1表示子弹的方位；
	private int x2, y2 = 0;// 敌方飞机初始方位
	private int x3, y3;// 功能包

	public List<Image> images_bg;// 背景
	public List<BulletMyPlane> bulletJPlanes;// 我方子弹集合
	public List<BulletEnemy> bulletEnemies;// 敌方子弹集合
	public List<Image> downPacks;
	public List<DownPack> downPackages;
	public List<Image> enemyImgs;
	public List<PlaneEnemy> enemies;// 敌机集合
	public List<BossBullet> bossBullets;// boss子弹集合
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
		button1.setActionCommand("重新开始");
		button2 = new JButton();
		button2.setLocation(200, 320);
		button2.setSize(50, 50);
		button2.setActionCommand("返回首页");

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
		downPacks.add(new ImageIcon(Config.packImg2).getImage());// 子弹包,type==2
		downPacks.add(new ImageIcon(Config.packImg1).getImage());// 药包,type==1
		downPacks.add(new ImageIcon(Config.packImg3).getImage());// 蓝包,type==3

		random = new Random();

		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		// TODO Auto-generated constructor stubs

		menu = new PopupMenu();
		menuItem1 = new MenuItem();
		menuItem1.setLabel("暂停游戏");

		MenuItem overItem = new MenuItem("结束游戏");
		MenuItem restartItem = new MenuItem("重新开始");

		MenuItem planeItem1 = new MenuItem("飞机类型一");
		MenuItem planeItem2 = new MenuItem("飞机类型二");

		Menu menuBulletType = new Menu("子弹类型");
		Menu planeMenu = new Menu("飞机类型");

		MenuItem itemBullet1 = new MenuItem("类型一(单弹)");
		MenuItem itemBullet2 = new MenuItem("类型二(扇形散弹)");
		MenuItem itemBullet3 = new MenuItem("类型三(直线散弹)");

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

		// 跟随
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

		// 绘制移动背景
		bgPaint(g);
		if (myPlane.isPass == false) {

			// 我方飞机
			myPlanePaint(g);

			// 绘制我方飞机子弹
			myBulletPaint(g);

			// 创建敌机子弹和绘制敌机
			enemyPlanePaint(g);

			// 绘制敌机子弹
			enemyBulletPaint(g);

			// 画功能包
			downPackPaint(g);

			// 画技能
			skillPaint(g);// 画技能倒计时

			// 技能功能
			if (pressSkill1) {
				Skills.addMyPlane(this, g);
			}
			if (pressSkill2) {
				Skills.protectSkill(this, g);

			}
			if (pressSkill3) {
				Skills.slowSpeed(this, g);
			}

			// 画boss及创建子弹
			bossPaint(g);

			// 画boss子弹
			drawBossBullet(g);

			// 画血条
			bloodPaint(g);

			// 画爆炸效果
			boomPaint(g);

			// 画蓝条
			bluePaint(g);

			// 判断是否游戏结束,即我方飞机血量是否为0
			isOver(g);

			myplanePaintAuto();
		} else {
			myPlane.draw2(g);// 飞机上升
			draw3(g);// 过关字符串
			if (myPlane.y < 0) {
				m++;
				myPlane.isPass = false;
				image = images_bg.get(m % 4);// 背景

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

	// 画技能倒计时
	private void skillPaint(Graphics g) {
		// TODO Auto-generated method stub
		if (blueNum < 10) {
			g.drawImage(new ImageIcon(Config.waitSkill).getImage(), 0,
					Config.frameHeight - 105, 60, 60, null);// q (僚机)
		} else {
			g.drawImage(new ImageIcon(Config.skill1).getImage(), 0,
					Config.frameHeight - 105, 60, 60, null);// q (僚机)
		}
		if (blueNum < 15) {
			g.drawImage(new ImageIcon(Config.waitSkill).getImage(), 65,
					Config.frameHeight - 105, 60, 60, null);// w(保护罩)

			g.drawImage(new ImageIcon(Config.waitSkill).getImage(), 130,
					Config.frameHeight - 105, 60, 60, null);// e(减速)
		} else {
			g.drawImage(new ImageIcon(Config.skill2).getImage(), 65,
					Config.frameHeight - 105, 60, 60, null);// w(保护罩)

			g.drawImage(new ImageIcon(Config.skill3).getImage(), 130,
					Config.frameHeight - 105, 60, 60, null);// e(减速)
		}
		if (blueNum < 20) {
			g.drawImage(new ImageIcon(Config.waitSkill).getImage(), 195,
					Config.frameHeight - 105, 60, 60, null);// r(大招)
		} else {
			g.drawImage(new ImageIcon(Config.skill4).getImage(), 195,
					Config.frameHeight - 105, 60, 60, null);// r(大招)
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

	// 技能一触发后计时开始
	private void startSkill1(Graphics g) {// 僚机q
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(n1), 5, Config.frameHeight - 60);
		n1--;
		if (n1 == 0) {
			pressSkill1 = false;
			StartSkill1Time = false;
			Skills.a = 0;
		}
	}

	// 技能二触发后开始计时
	private void startSkill2(Graphics g) {// 保护盾
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(n2), 66, Config.frameHeight - 60);
		n2--;
		if (n2 == 0) {
			Skills.a1 = 0;
			pressSkill2 = false;
			StartSkill2Time = false;
		}
	}

	// 技能三触发后开始计时2
	private void startSkill3(Graphics g) {// 减速
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

	// 技能四触发后开始计时
	private void startSkill4(Graphics g) {// r
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(n4), 199, Config.frameHeight - 60);
		n4--;
		if (n4 <= 0) {
			pressSkill4 = false;
		}
	}

	// 背景
	private void bgPaint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, 0, bg_y, Config.frameWidth, Config.frameHeight, null);// 背景
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

	// 我方飞机
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

	// 画我方飞机子弹
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

	// 创敌机 画敌机和子弹
	private void enemyPlanePaint(Graphics g) {
		// TODO Auto-generated method stub

		if (a % (50 - speed * m) == 0) {// 创建敌机和子弹
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
			if (a1 % 2 == 0) {// 创建敌机子弹
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
		// 画敌机
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

	// 画敌机子弹
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

	// 画功能包
	private void downPackPaint(Graphics g) {
		// TODO Auto-generated method stubs
		// 创建功能包
		if (a % 1000 == 0) {
			// 创建药包
			if (changePack) {
				x3 = random.nextInt(Config.frameWidth - 30);
				DownPack package1 = new DownPack(x3, y3, downPacks, 1);
				downPackages.add(package1);
				if (m >= 4) {
					changePack = false;
				}
			} else if (!changePack) {
				if (m >= 4) {// 创建蓝包
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

	// 画boss
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
			boss.n = 0;// 又可以画boss大招
			boss.x = Config.frameWidth / 2 - 122;
			boss.y = 0;
			boss.a = 0;

			pass_grade = myPlane.grade;// 记录分数
			a2 = 0;
			// 清空集合
			enemies.removeAll(enemies);
			booms.removeAll(booms);
			bulletEnemies.removeAll(bulletEnemies);
			bulletJPlanes.removeAll(bulletJPlanes);
			bossBullets.removeAll(bossBullets);

		}
	}

	// 画boss子弹
	private void drawBossBullet(Graphics g) {
		// TODO Auto-generated method stub
		if (bossBullets.size() > 0) {
			for (int i = 0; i < bossBullets.size(); i++) {
				BossBullet bossBullet = bossBullets.get(i);

				if (bossBullet.type == 3) {// 激光
					bossBullet.move(boss.x + 95);
					bossBullet.draw2(g);
				} else {// 扇形子弹
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

	// 画血量与得分
	private void bloodPaint(Graphics g) {
		// TODO Auto-generated method stub
		g.setFont(Config.font);
		g.setColor(Color.WHITE);
		buffer = new StringBuffer("血量剩余:  " + myPlane.blood);
		buffer2 = new StringBuffer("当前得分:  " + myPlane.grade);

		g.setColor(Color.WHITE);

		g.drawString(buffer.toString(), Config.frameWidth - 300, 80);
		g.drawString(buffer2.toString(), Config.frameWidth - 300, 130);
		g.drawString("当前关卡数：" + String.valueOf(m), Config.frameWidth - 300, 180);

		g.setFont(new Font("", Font.BOLD, 13));
		g.drawString("我方血量:", 0, 15);
		g.setColor(Color.red);
		g.drawRect(60, 5, Config.frameWidth - 60, 10);
		g.fillRect(60, 5, (Config.frameWidth - 60) * myPlane.blood
				/ myPlanBlood, 10);

		if (myPlane.showBoss == true && boss.bossBlood > 0) {
			g.setColor(Color.WHITE);
			g.drawString("BOSS血量:", 0, 50);
			g.setColor(Color.red);
			g.drawRect(70, 40, Config.frameWidth - 80, 10);
			g.fillRect(70, 40, (Config.frameWidth - 80) * boss.bossBlood
					/ bossBlood, 10);
		}

	}

	// 画爆炸
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

	// 判断游戏结束
	private void isOver(Graphics g) {
		// TODO Auto-generated method stub
		if (myPlane.gameOver == true) {
			stopThread();
			

			
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			this.add(button1);
			this.add(button2);
//			button1.setEnabled(true);
//			button2.setEnabled(true);

			draw2(g);// 画结束项目（背景与按钮绘制）

		}
	}

	// 画结束字符串
	public void draw2(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image2, 0, 0, this);

		g.setColor(new Color(233, 230, 234));
		g.fillOval(190, 190, 70, 70);
		g.fillOval(190, 313, 70, 70);
		g.setColor(new Color(148, 158, 158));
		g.drawString("重新开始", 195, 227);
		g.drawString("返回首页", 195, 352);

		g.setFont(Config.font);
		g.setColor(Color.WHITE);
		g.drawString("游戏结束！！", 150, 450);

	}

	// 画胜利字符串
	public void draw3(Graphics g) {
		// TODO Auto-generated method stub

		g.setColor(Color.WHITE);
		g.setFont(Config.font);
		g.drawString("游戏过关!！即将进入下一关......", 20, 350);
	}

	/**
	 * 
	 * 屏幕鼠标点击监听事件方法
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

			if (n <= 5) {// 如果发了5发超级子弹就回复为普通子弹
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

	// 键盘监听事件方法
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

	// 结束游戏

	public void stopThread() {
		// TODO Auto-generated method stub
		this.reStart = false;

	}

	// 开始游戏
	private void startGame() {
		// TODO Auto-generated method stub
		reStart = true;
		startThread();
	}

	// 重置并开始线程
	public void reStart() {
		// TODO Auto-generated method stub
		// 集合
		enemies.removeAll(enemies);
		booms.removeAll(booms);
		bulletEnemies.removeAll(bulletEnemies);
		bulletJPlanes.removeAll(bulletEnemies);
		downPackages.removeAll(downPackages);
		bossBullets.removeAll(bossBullets);

		// 我机
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
		boss.n = 0;// 又可以画boss大招
		boss.x = Config.frameWidth / 2 - 122;
		boss.y = 0;
		boss.a = 0;

		pass_grade = 0;

		// 关数
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
		if (str.equals("暂停游戏")) {
			this.reStart = false;
			menuItem1.setLabel("开始游戏");
		}
		if (str.equals("开始游戏")) {
			startGame();
			menuItem1.setLabel("暂停游戏");
		}
		if (str.equals("类型一(单弹)")) {
			buyType = 1;
		}
		if (str.equals("类型二(扇形散弹)")) {
			buyType = 2;
		}
		if (str.equals("类型三(直线散弹)")) {
			buyType = 3;
		}
		if (str.equals("结束游戏")) {
			myPlane.gameOver = true;
		}
		if (str.equals("飞机类型一")) {
			myPlane.image = new ImageIcon(Config.startGameimgMy).getImage();
			myplaneType = 1;
		}
		if (str.equals("飞机类型二")) {
			myPlane.image = new ImageIcon(Config.myPlaneImg2).getImage();
			myplaneType = 2;
		}
		if (e.getSource() == button1) {
			if (str.equals("重新开始")) {
				this.remove(button1);
				this.remove(button2);
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
				reStart();
				// 写分数
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
			if (str.equals("重新开始")) {
				myPlane.gameOver = true;
			}
		}
		if (str.equals("返回首页")) {
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
