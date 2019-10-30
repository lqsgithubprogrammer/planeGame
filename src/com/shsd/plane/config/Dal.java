package com.shsd.plane.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dal {
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;

	// ִ��sql��䷵�ؽ����
	public ResultSet executeQuSQL(String sql) throws Exception {

		st = conn.createStatement();
		rs = st.executeQuery(sql);
		return rs;

	}

	public void executeSQL(String sql) throws Exception {

		st = conn.createStatement();
		st.execute(sql);

	}

	// ͨ��userid��ȡ�û��ѽ����
	public String getMoney(int id) throws Exception {
		// TODO Auto-generated method stub
		String money = "";
		conn = ConnUtill.getConnection();
		String sql = "SELECT users.money FROM users where usersid=" + id;
		rs = executeQuSQL(sql);
		if (rs != null) {
			while (rs.next()) {
				money = rs.getString("money");
			}
		}
		rs.close();
		st.close();
		conn.close();
		return money;
	}

	// ͨ��userid��ȡ�û��ѽ����
	public String setMoney(int id, String money) throws Exception {
		// TODO Auto-generated method stub
		conn = ConnUtill.getConnection();
		String startMoney = getMoney(id);
		String sql = "update  users set money=" + (startMoney + money)
				+ " where usersid=" + id;
		executeSQL(sql);
		st.close();
		conn.close();
		return money;
	}

	// ͨ��userid��ȡ�û��ѹ���ķɻ�
	// public int getPlaneHave(int id) throws Exception {
	// // TODO Auto-generated method stub
	// int money = 0;
	// conn = ConnUtill.getConnection();
	// String sql = "SELECT users.money FROM users where usersid=" + id;
	// rs = executeQuSQL(sql);
	// if (rs != null) {
	// while (rs.next()) {
	// money = rs.getInt("money");
	// }
	// }
	// rs.close();
	// st.close();
	// conn.close();
	// return money;
	// }

	// ��ȡǰ10�ķ������û�
	public List<String> getScores() throws Exception {
		// TODO Auto-generated method stub
		List<String> list = null;
		conn = ConnUtill.getConnection();
		String sql = "SELECT scores.userid, scores.scores FROM scores order by scores desc limit 10 ";
		rs = executeQuSQL(sql);
		if (rs != null) {
			list = new ArrayList<>();
			while (rs.next()) {
				list.add(rs.getInt("userid") + "-" + rs.getString("scores"));
			}
		}
		rs.close();
		st.close();
		conn.close();
		return list;

	}

	// ����û������õķ���
	public void addscores(int i, String score) throws Exception {
		// TODO Auto-generated method stub
		conn = ConnUtill.getConnection();
		String sql = "INSERT INTO  scores(userid,scores) VALUES(" + i + ",'"
				+ score + "');";
		executeSQL(sql);

		st.close();
		conn.close();
	}

}
