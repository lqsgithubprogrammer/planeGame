package com.shsd.plane.config;

import java.awt.Font;
import java.awt.Toolkit;

public class Config {
	
	
	public static Font font = new Font("", Font.BOLD, 30);
	
	public final static String welcomeMu="sound/game_music.wav";//��Ϸ��������
	public final static String shootMyPlane="sound/fire_bullet.wav";//��Ϸ�ɻ������ӵ�
	public final static String enemyBoom="sound/get_bomb.wav";//�л���ը
	public final static String bossBoom="sound/use_bomb.wav";//boss��ը
	public final static String boosShoot="sound/small_plane_killed.wav";//boss����
	
	
	public final static String frameIconeUrl="images/wsparticle_41.png";
	public final static String panelBgImg="images/bg3.jpg";
	public final static String logoImage="images/enemy5.png";
	public final static String button_bg="images/button_bg.png";
	public final static String button_hover_bg="images/button_hover_bg.png";
	public final static String bullet_flash="images/bossbullet.png";
	public final static String bottomlogoImage="images/enemy12.png";
	
	//����ҳ��
	public final static String loginGameimage1="images/enemy5.png";//����ҳ��ɻ�ͼƬ
	public final static String loginGameimage2="images/bomb3.png";//����ҳ��ɻ�β��ͼƬ
	public final static String loginGamebg="images/bckground.png";//����ҳ�汳��ͼƬ
	
	
	//��ʼ��Ϸҳ��

	public final static String startGameimg1="images/enemy12.png";//
	public final static String startGameimgMy="images/fly_zhanshi1.png";//��ʼҳ���ҷ��ɻ�
	public final static String startGameBullet="images/bossbullet.png";//�ҷ��ӵ�
	public final static String enemyPlane="images/enemy6.png";//�л�
	public final static String boomImage="images/wsparticle_41.png";//�л���ը
	public final static String bulletEnemy="images/bomb18.png";//�л��ӵ�
	public final static String boomMyImg="images/boom2.png";//�ҷ���ըͼƬ
	public final static String bossEnemy="images/enemy13.png";//boss
	public final static String bossBullet1="images/bossbullet_boss.png";//boss�ӵ�1
	public final static String bossBullet2="images/bomb18.png";//boss�ӵ�2
	public final static String bossBullet3="images/bossBullet2.png";//boss�ӵ�3
	public final static String packImg1="images/shoot2.png";//�����1
	public final static String packImg2="images/pack.png";//�����2
	public final static String packImg3="images/bluePack.png";//�����3
	
	
	public final static String gameOver="images/gameOver1.png";//��Ϸ����ѡ�����
	
	
	//�ڶ���

	public final static String waitSkill="images/waitSkill.png";//������
	
	public final static String skill1="images/skill1.png";//����1
	
	public final static String skill2="images/skill2.png";//����2
	public final static String skill2Play="images/skill2Play.png";//����2�ļ���ʩչ
	public final static String skill3="images/skill3.png";//����3
	
	public final static String skill4="images/skill4.png";//����4
	
	public final static String slowBg="images/slowdingbg.png";//���ܱ���
	
	public final static String startGamebg1="images/bg1.jpg";//����1
	public final static String startGamebg2="images/bg2.jpg";//����2
	public final static String startGamebg3="images/bckground.png";//����3
	public final static String startGamebg4="images/bg4.jpg";//����4
	public final static String enemyPlane1="images/enem5.png";//�л�1
	public final static String enemyPlane2="images/enemy0.png";//�л�2
	public final static String enemyPlane3="images/enemy4.png";//�л�3
	public final static String enemyBoom2="images/wsparticle_02.png";//�л�δ����ըͼ
	public final static String myPlaneImg2="images/e5.png";//�ڶ����ҷ��ɻ�ͼ
	public final static String addMyPlane="images/fly_zhanshi3.png";//�Ż�ͼƬ
	
	
	public final static int frameWidth=(int)(0.25* Toolkit.getDefaultToolkit().getScreenSize().getWidth());//frame����Ŀ��
	public final static int frameHeight=(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-300;//frame����ĸ߶�
	public final static int frameStartWidth=(int)(0.5* Toolkit.getDefaultToolkit().getScreenSize().getWidth()-frameWidth*0.5);//frame�������ʼ��x����
	public final static int frameStartHeight=(int)(0.5* Toolkit.getDefaultToolkit().getScreenSize().getHeight()-frameHeight*0.5);//frame�������ʼ��y����
}
