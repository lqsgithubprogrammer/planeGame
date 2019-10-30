package com.shsd.plane.UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.shsd.plane.config.Dal;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class RankJPanel extends JPanel {
	List<String> list;
	Dal dal;
	JPanel panel_2;
	JPanel panel_4;
	JPanel panel_3;

	/**
	 * Create the panel.
	 */
	public RankJPanel() {
		dal = new Dal();

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label = new JLabel("\u6392\u884C\u699C");
		label.setFont(new Font("Ò¶¸ùÓÑÃ«±ÊÐÐÊé2.0°æ", Font.PLAIN, 33));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_5 = new JPanel();
		add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("\u540D\u6B21");
		panel_6.add(lblNewLabel, BorderLayout.NORTH);
		
				panel_2 = new JPanel();
				panel_6.add(panel_2, BorderLayout.CENTER);
				panel_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
				panel_2.setLayout(new GridLayout(10, 1, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237");
		panel_7.add(lblNewLabel_1, BorderLayout.NORTH);
		
				panel_4 = new JPanel();
				panel_7.add(panel_4, BorderLayout.CENTER);
				panel_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
				panel_4.setLayout(new GridLayout(10, 1, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("\u5206\u6570");
		panel_8.add(lblNewLabel_2, BorderLayout.NORTH);
		
				panel_3 = new JPanel();
				panel_8.add(panel_3, BorderLayout.CENTER);
				panel_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
				panel_3.setLayout(new GridLayout(10, 1, 0, 0));
		
		
	}

	public void refresh() {
		// TODO Auto-generated method stub

		try {
			list = dal.getScores();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			String[] s2 = s.split("-");
			if (s2.length > 0) {
				JLabel jLabel1 = new JLabel("µÚ" +( i + 1) + "Ãû£º");
				panel_2.add(jLabel1);
				JLabel jLabel2 = new JLabel(s2[0]);
				panel_4.add(jLabel2);
				JLabel jLabel3 = new JLabel(s2[1]);
				panel_3.add(jLabel3);
			}

		}
	}
	
	public void clear() {
		// TODO Auto-generated method stub
		panel_2.removeAll();
		panel_4.removeAll();
		panel_3.removeAll();
	}

}
