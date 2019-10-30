package com.shsd.plane.UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.shsd.plane.config.Config;

public class PlaneButton extends JButton {
	private Image button_bg;
	private Image button_hover_bg;
	private String button_text;
	private boolean button_type = true;// ����ڰ�ť��

	public PlaneButton(String string) {
		// ���ð�ť����ʾ����
		this.button_text = string;
		this.setText(string);
		button_bg = new ImageIcon(Config.button_bg).getImage();
		button_hover_bg = new ImageIcon(Config.button_hover_bg).getImage();
		this.setFocusable(false);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setPreferredSize(new Dimension(160, 45));
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);

		// ������
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				// �ͷ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				// �Ƴ�
				button_type = true;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				// �ƶ�����ť��
				button_type = false;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// ���
			}
		});
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (button_type) {
			g.drawImage(button_bg, 0, 0, 160, 45, null);
			g.setColor(Color.RED);
		} else {
			g.drawImage(button_hover_bg, 0, 0, 160, 45, null);
			g.setColor(Color.RED);
		}
		
		//���㰴ť��ʾ��λ��
		int x, y=0;
		//��������ľ���
		FontMetrics f= g.getFontMetrics();
		//��ȡ button_text��С
		Rectangle2D rect = f.getStringBounds(button_text, g);
		x=(int) (160/2-rect.getWidth()+30);
		y=23;
		
		g.drawString(button_text, x, y);
		

	}
}
