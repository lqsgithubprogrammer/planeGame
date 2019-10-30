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
	private boolean button_type = true;// 鼠标在按钮外

	public PlaneButton(String string) {
		// 设置按钮的显示内容
		this.button_text = string;
		this.setText(string);
		button_bg = new ImageIcon(Config.button_bg).getImage();
		button_hover_bg = new ImageIcon(Config.button_hover_bg).getImage();
		this.setFocusable(false);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setPreferredSize(new Dimension(160, 45));
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);

		// 鼠标监听
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				// 释放
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				// 移出
				button_type = true;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				// 移动到按钮上
				button_type = false;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// 点击
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
		
		//计算按钮显示的位置
		int x, y=0;
		//定义字体的矩阵
		FontMetrics f= g.getFontMetrics();
		//获取 button_text大小
		Rectangle2D rect = f.getStringBounds(button_text, g);
		x=(int) (160/2-rect.getWidth()+30);
		y=23;
		
		g.drawString(button_text, x, y);
		

	}
}
