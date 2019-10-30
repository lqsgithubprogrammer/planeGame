package com.shsd.plane.config;

import java.awt.Font;
import java.awt.Toolkit;

public class Config {
	
	
	public static Font font = new Font("", Font.BOLD, 30);
	
	public final static String welcomeMu="sound/game_music.wav";//游戏整个音乐
	public final static String shootMyPlane="sound/fire_bullet.wav";//游戏飞机发射子弹
	public final static String enemyBoom="sound/get_bomb.wav";//敌机爆炸
	public final static String bossBoom="sound/use_bomb.wav";//boss爆炸
	public final static String boosShoot="sound/small_plane_killed.wav";//boss击中
	
	
	public final static String frameIconeUrl="images/wsparticle_41.png";
	public final static String panelBgImg="images/bg3.jpg";
	public final static String logoImage="images/enemy5.png";
	public final static String button_bg="images/button_bg.png";
	public final static String button_hover_bg="images/button_hover_bg.png";
	public final static String bullet_flash="images/bossbullet.png";
	public final static String bottomlogoImage="images/enemy12.png";
	
	//加载页面
	public final static String loginGameimage1="images/enemy5.png";//加载页面飞机图片
	public final static String loginGameimage2="images/bomb3.png";//加载页面飞机尾焰图片
	public final static String loginGamebg="images/bckground.png";//加载页面背景图片
	
	
	//开始游戏页面

	public final static String startGameimg1="images/enemy12.png";//
	public final static String startGameimgMy="images/fly_zhanshi1.png";//开始页面我方飞机
	public final static String startGameBullet="images/bossbullet.png";//我方子弹
	public final static String enemyPlane="images/enemy6.png";//敌机
	public final static String boomImage="images/wsparticle_41.png";//敌机爆炸
	public final static String bulletEnemy="images/bomb18.png";//敌机子弹
	public final static String boomMyImg="images/boom2.png";//我方爆炸图片
	public final static String bossEnemy="images/enemy13.png";//boss
	public final static String bossBullet1="images/bossbullet_boss.png";//boss子弹1
	public final static String bossBullet2="images/bomb18.png";//boss子弹2
	public final static String bossBullet3="images/bossBullet2.png";//boss子弹3
	public final static String packImg1="images/shoot2.png";//掉落包1
	public final static String packImg2="images/pack.png";//掉落包2
	public final static String packImg3="images/bluePack.png";//掉落包3
	
	
	public final static String gameOver="images/gameOver1.png";//游戏结束选择界面
	
	
	//第二关

	public final static String waitSkill="images/waitSkill.png";//蓝不足
	
	public final static String skill1="images/skill1.png";//技能1
	
	public final static String skill2="images/skill2.png";//技能2
	public final static String skill2Play="images/skill2Play.png";//技能2的技能施展
	public final static String skill3="images/skill3.png";//技能3
	
	public final static String skill4="images/skill4.png";//技能4
	
	public final static String slowBg="images/slowdingbg.png";//技能背景
	
	public final static String startGamebg1="images/bg1.jpg";//背景1
	public final static String startGamebg2="images/bg2.jpg";//背景2
	public final static String startGamebg3="images/bckground.png";//背景3
	public final static String startGamebg4="images/bg4.jpg";//背景4
	public final static String enemyPlane1="images/enem5.png";//敌机1
	public final static String enemyPlane2="images/enemy0.png";//敌机2
	public final static String enemyPlane3="images/enemy4.png";//敌机3
	public final static String enemyBoom2="images/wsparticle_02.png";//敌机未死爆炸图
	public final static String myPlaneImg2="images/e5.png";//第二个我方飞机图
	public final static String addMyPlane="images/fly_zhanshi3.png";//僚机图片
	
	
	public final static int frameWidth=(int)(0.25* Toolkit.getDefaultToolkit().getScreenSize().getWidth());//frame窗体的宽度
	public final static int frameHeight=(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-300;//frame窗体的高度
	public final static int frameStartWidth=(int)(0.5* Toolkit.getDefaultToolkit().getScreenSize().getWidth()-frameWidth*0.5);//frame窗体的起始点x坐标
	public final static int frameStartHeight=(int)(0.5* Toolkit.getDefaultToolkit().getScreenSize().getHeight()-frameHeight*0.5);//frame窗体的起始点y坐标
}
