package com.shsd.plane.UI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.shsd.plane.config.Config;
import com.shsd.plane.config.Dal;
import com.shsd.plane.config.FileConfig;

public class GameJFrame extends JFrame implements ActionListener,
		WindowListener {
	static CardLayout cardLayout;
	static JPanel jPanel_main;// 卡片容器
	public static GameStartJPanel gsp;// 正式游戏页面
	public static RankJPanel rank;
	WelcomeMyGameJPanel welcomeMyGameJPanel;// 首页
	PlaneLogin login;// 加载页面
	JMenuItem item = null;

	public GameJFrame(String string) {
		// TODO Auto-generated constructor stub
		setAlwaysOnTop(true);
		setResizable(false);
		this.setIconImage(new ImageIcon(Config.frameIconeUrl).getImage());
		this.setBounds(Config.frameStartWidth, Config.frameStartHeight,
				Config.frameWidth, Config.frameHeight);
		addMenu();
		jPanel_main = new JPanel();
		this.setTitle(string);
		this.setVisible(true);

		jPanel_main.requestFocus();
		jPanel_main = new JPanel();

		cardLayout = new CardLayout();
		jPanel_main.setLayout(cardLayout);

		
		login = new PlaneLogin();
		gsp = new GameStartJPanel();
		rank= new RankJPanel();
		welcomeMyGameJPanel = new WelcomeMyGameJPanel(login,this);
		jPanel_main.add("game_welcome", welcomeMyGameJPanel);
		jPanel_main.add("game_start", gsp);
		jPanel_main.add("game_login", login);
		jPanel_main.add("game_shop", new ShopJPanel());
		jPanel_main.add("game_help", new HelpJpanel());
		jPanel_main.add("game_rank", rank);
		jPanel_main.add("game_set", new GameSetJPanel());

		this.add(jPanel_main);

		this.addWindowListener(this);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}

	private void addMenu() {

		JMenuBar jMenuBar = new JMenuBar();
		JMenu jMenu = new JMenu("选项");
//		JMenu jMenu2 = new JMenu("退出");

		item = new JMenuItem("首页");
		jMenu.add(item);

//		JMenuItem jMenuItem6 = new JMenuItem("退出");
//		jMenu2.add(jMenuItem6);
		// 菜单栏
		jMenuBar.add(jMenu);
//		jMenuBar.add(jMenu2);

		this.setJMenuBar(jMenuBar);

		item.addActionListener(this);

//		jMenuItem6.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		if (str.equals("首页")) {
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_welcome");
			// 加载页面停止
			login.stopThread();
			// 正式游戏页面停止
			gsp.stopThread();
		} else if (str.equals("退出")) {
			System.exit(0);
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		gsp.stopThread();
		if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
				this, "确定退出？", "提示",
				JOptionPane.OK_CANCEL_OPTION)) {
			// 写分数
			if (gsp.myPlane.gameOver==true && !welcomeMyGameJPanel.isShowing()) {
				Dal dal = new Dal();
				try {
					dal.addscores(1, String.valueOf(gsp.myPlane.grade));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
//			FileConfig.createFile("scores");
//			FileConfig.writeByByte("scores", String.valueOf(gsp.myPlane.grade));
			System.exit(0);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
