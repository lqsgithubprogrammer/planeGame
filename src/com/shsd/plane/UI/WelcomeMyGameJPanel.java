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
	private Image bg;// ����
	private ArrayList<IndexAmin> arrayList = new ArrayList<>();// ����
	public boolean isStartLogin;// �Ƿ�ʼ����
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
		
		// ���屳��
		bg = new ImageIcon(Config.panelBgImg).getImage();
		// �������
		Font font = Config.font; // ����logo
		JLabel logo = new JLabel(new ImageIcon(Config.logoImage));
		JLabel logo_name = new JLabel("��ӭ��ĵ�������");
		// ��logo���������С
		logo_name.setFont(font);
		logo_name.setForeground(Color.BLUE);
		// ��ť
		PlaneButton jButton1 = new PlaneButton("��ʼ��Ϸ");
		PlaneButton jButton2 = new PlaneButton("��Ϸ����");
		PlaneButton jButton3 = new PlaneButton("��Ϸ����");
		PlaneButton jButton4 = new PlaneButton("��Ϸ�̵�");
		PlaneButton jButton5 = new PlaneButton("��Ϸ���а�");
		PlaneButton jButton6 = new PlaneButton("��Ϸ��¼");
		PlaneButton jButton7 = new PlaneButton("�˳���Ϸ");
		// �Բ˵����������
		jButton1.addActionListener(this);
		jButton2.addActionListener(this);
		jButton3.addActionListener(this);
		jButton4.addActionListener(this);
		jButton5.addActionListener(this);
		jButton6.addActionListener(this);
		jButton7.addActionListener(this);

		// ��Ȩ���ײ�ͼƬ
		JLabel plane_info = new JLabel("��Ȩ���У�����ؾ���������");
		JLabel bottomLogo = new JLabel(new ImageIcon(Config.bottomlogoImage));
		// ����
		JLabel plane_info1 = new JLabel("����");
		plane_info1.setFont(font);
		JLabel plane_info2 = new JLabel("����");
		plane_info2.setFont(font);

		// ����frame���岼��
		JPanel main = new JPanel();
		main.setOpaque(false);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(30);
		// �������岼��
		main.setLayout(borderLayout);

		// logo����
		JPanel logo_jpanel = new JPanel();
		// logo����͸��
		logo_jpanel.setOpaque(false);
		// ���logo����
		logo_jpanel.add(logo);
		// logo_jpanel.add(logo_name);

		// ѡ������
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

		// ��Ȩ����
		GridLayout gridLayout = new GridLayout(2, 1);
		JPanel info = new JPanel();
		info.setLayout(gridLayout);
		info.setOpaque(false);
		info.add(plane_info);
		info.add(bottomLogo);

		
		main.add(logo_jpanel, borderLayout.NORTH);
		// ��ťѡ��
		main.add(botton_jpanel, borderLayout.CENTER);
		// ��Ȩ
		main.add(info, borderLayout.SOUTH);
		// ����
	
		this.add(main);

	}// ���캯������
		

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
		// ���ӵ�����
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
		// ��ȡ�û������ť
		String str = e.getActionCommand();
		if (str.equals("�˳���Ϸ")) {
			System.exit(0);
		} else if (str.equals("��ʼ��Ϸ")) {
			// ��ת����Ϸ����ҳ��
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_login");
			login.restart();

		} else if (str.equals("��Ϸ�̵�")) {
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_shop");
		} else if (str.equals("��Ϸ����")) {
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_help");
		} else if (str.equals("��Ϸ���а�")) {
			GameJFrame.cardLayout.show(GameJFrame.jPanel_main, "game_rank");
			frame.rank.clear();
			frame.rank.refresh();
		}
	}

}