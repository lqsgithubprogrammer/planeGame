package com.shsd.plane.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.security.auth.login.LoginContext;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.shsd.plane.config.Config;
import com.shsd.plane.entity.IndexAmin;

public class WelcomeMyGameJPanel extends JPanel implements ActionListener {
	// private int x = 100, y = 100;
	private Image bg;// 背景
	private ArrayList<IndexAmin> arrayList = new ArrayList<>();// 动画
	public boolean isStartLogin;// 是否开始加载
	PlaneLogin login;
	GameJFrame frame;
	private int k = 0;
	int x = Config.frameWidth / 2 - 40;
	int y = Config.frameHeight -150;
	IndexAmin amin;
	List<IndexAmin> amins;

	public WelcomeMyGameJPanel(PlaneLogin login, GameJFrame gameJframe) {
		amins = new ArrayList<>();
		this.startAmin();
		this.frame = gameJframe;
		this.login = login;
		
		// 窗体背景
		bg = new ImageIcon(Config.panelBgImg).getImage();
		// 字体对象
		Font font = Config.font; // 标题logo
		JLabel logo = new JLabel(new ImageIcon(Config.logoImage));
		JLabel logo_name = new JLabel("欢迎你的到来，！");
		// 给logo设置字体大小
		logo_name.setFont(font);
		logo_name.setForeground(Color.BLUE);
		// 按钮
		PlaneButton jButton1 = new PlaneButton("开始游戏");
		PlaneButton jButton2 = new PlaneButton("游戏帮助");
		PlaneButton jButton3 = new PlaneButton("游戏设置");
		PlaneButton jButton4 = new PlaneButton("游戏商店");
		PlaneButton jButton5 = new PlaneButton("游戏排行榜");
		PlaneButton jButton6 = new PlaneButton("游戏登录");
		PlaneButton jButton7 = new PlaneButton("退出游戏");
		// 对菜单做点击监听
		jButton1.addActionListener(this);
		jButton2.addActionListener(this);
		jButton3.addActionListener(this);
		jButton4.addActionListener(this);
		jButton5.addActionListener(this);
		jButton6.addActionListener(this);
		jButton7.addActionListener(this);

		// 版权及底部图片
		JLabel plane_info = new JLabel("版权所有，盗版必究！！！！");
		JLabel bottomLogo = new JLabel(new ImageIcon(Config.bottomlogoImage));
		// 动画
		JLabel plane_info1 = new JLabel("动画");
		plane_info1.setFont(font);
		JLabel plane_info2 = new JLabel("动画");
		plane_info2.setFont(font);

		// 创建frame整体布局
		JPanel main = new JPanel();
		main.setOpaque(false);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(30);
		// 设置整体布局
		main.setLayout(borderLayout);

		// logo容器
		JPanel logo_jpanel = new JPanel();
		// logo容器透明
		logo_jpanel.setOpaque(false);
		// 添加logo内容
		logo_jpanel.add(logo);
		// logo_jpanel.add(logo_name);

		// 选项容器
		JPanel botton_jpanel = new JPanel();
		botton_jpanel.setOpaque(false);
		GridLayout gridLayout2 = new GridLayout(7, 1, 20, 20);
		botton_jpanel.setLayout(gridLayout2);
		botton_jpanel.add(jButton1);
		botton_jpanel.add(jButton2);
		botton_jpanel.add(jButton3);
		botton_jpanel.add(jButton4);
		botton_jpanel.add(jButton5);
		botton_jpanel.add(jButton6);
		botton_jpanel.add(jButton7);

		// 版权容器
		GridLayout gridLayout = new GridLayout(2, 1);
		JPanel info = new JPanel();
		info.setLayout(gridLayout);
		info.setOpaque(false);
		info.add(plane_info);
		info.add(bottomLogo);

		
		main.add(logo_jpanel, borderLayout.NORTH);
		// 按钮选项
		main.add(botton_jpanel, borderLayout.CENTER);
		// 版权
		main.add(info, borderLayout.SOUTH);
		// 动画
	
		this.add(main);

	}// 构造函数结束
		

	public void startAmin() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					repaint();
					try {
						Thread.sleep(100);
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
		g.drawImage(bg, 0, 0, Config.frameWidth, Config.frameHeight, null);
		// 画子弹动画
		if (k % 6 == 0) {
			for (int i = 0; i < 10; i++) {
				IndexAmin amin = new IndexAmin((i - 5) * 2, x + i * 5, y, 35,
						35, new ImageIcon(Config.bullet_flash).getImage(), 0);
				amins.add(amin);
			}
		}
		if (amins.size() > 0) {
			for (int i = 0; i < amins.size(); i++) {
				amin = amins.get(i);
				amin.draw(g);
				amin.run(5);
			}
		}
		k++;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 获取用户点击按钮
		String str = e.getActionCommand();
		if (str.equals("退出游戏")) {
			System.exit(0);
		} else if (str.equals("开始游戏")) {
			// 跳转到游戏加载页面
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_login");
			login.restart();

		} else if (str.equals("游戏商店")) {
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_shop");
		} else if (str.equals("游戏帮助")) {
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_help");
		} else if (str.equals("游戏排行榜")) {
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_rank");
			frame.rank.clear();
			frame.rank.refresh();
		}
	}

}