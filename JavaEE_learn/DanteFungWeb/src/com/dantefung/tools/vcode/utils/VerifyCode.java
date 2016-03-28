package com.dantefung.tools.vcode.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCode {

	private int w = 70;//宽
	private int h = 35;//高
	private Color bgcolor = new Color(250, 250, 250);//背景颜色
	private Random random = new Random();
	private StringBuilder sb= new StringBuilder(4);
	private String text;
	/**
	 * 获取随机数
	 */
	private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
	private String randomChar() {
		int index = random.nextInt(codes.length());
		return  codes.charAt(index)+"";
	}
	/**
	 * 随机得到背景颜色
	 */
	private Color randomColor() {
		int r = random.nextInt(150);
		int g = random.nextInt(150);
		int b = random.nextInt(150);
		return new Color(r, g, b);
	}
	/**
	 * 画线
	 * @param img
	 */
	private void drawline(BufferedImage img){
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(1.5F));//比平常的线粗1.5倍
		for(int i=0;i<5;i++){
			int x1 = random.nextInt(w);
			int y1 = random.nextInt(h);
			int x2 = random.nextInt(w);
			int y2 = random.nextInt(h);
			g.drawLine(x1, y1, x2, y2);
			
		}
	}
	/**
	 * 随机得到字体的大小，样式，颜色
	 */
	private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
	private Font randomFont() {
		int index = random.nextInt(fontNames.length);
		String fontName = fontNames[index];
		int size = random.nextInt(5)+24;
		int style = random.nextInt(4);
		return new Font(fontName,style,size); 
		
	}
  

	/**
	 * 创建图片
	 * 
	 * @return
	 */
	private BufferedImage createImage() {
		/*
		 * 创建图片 设置背景
		 */
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		img.getGraphics().setColor(bgcolor);
		//填充整个画布，形成背景色
		img.getGraphics().fillRect(0, 0, w, h);
		return img;
	}
	/**
	 * 获取生成的图片
	 * @return
	 */
	public BufferedImage getImage(){
		BufferedImage img = createImage();
		Graphics g = img.getGraphics();
		for(int i = 0;i<4;i++){
			String ch = this.randomChar();
		    sb.append(ch);//记录生成的字符
			g.setColor(this.randomColor());
			g.setFont(this.randomFont());
			g.drawString(ch, w/4*i, h-5);
		}
		text = sb.toString();
		this.drawline(img);
		return img;
	}
	/**
	 * 返回验证码图上的字
	 * @return
	 */
	public String getText(){
		return text;
	}
	/**
	 * 保存图片
	 * 
	 * @throws IOException
	 */
	public static void save(BufferedImage img,OutputStream out)
			throws IOException {
		ImageIO.write(img, "JPEG", out);
	}
}
