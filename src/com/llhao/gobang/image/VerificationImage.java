package com.llhao.gobang.image;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 验证码图片类，用于绘制一幅验证码图片
 * 
 * @author 罗浩
 **/
public class VerificationImage extends BufferedImage {
	public static int WIDTH_DEFAULT = 200;
	public static int HEIGHT_DEFAULT = 75;
	public static Font FONT_DEFAULT = new Font("宋体", 0, 70);
	private Font font = FONT_DEFAULT;
	private String text;
	private List<ImageNoise> noises = new ArrayList<ImageNoise>();

	/**
	 * 创建一个验证码图片，指定长度宽度和内容文字
	 * 
	 * @param width
	 * @param height
	 * @param text
	 */
	public VerificationImage(int width, int height, String text) {
		super(width, height, BufferedImage.TYPE_INT_RGB);
		this.text = text;
	}

	/**
	 * 创建一张验证码图片，长度为默认值，内容为给定字符串
	 * 
	 * @param text
	 */
	public VerificationImage(String text) {
		this(WIDTH_DEFAULT, HEIGHT_DEFAULT, text);
	}

	/**
	 * 绘制图片
	 */
	public void create() {
		Graphics g = this.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.setFont(font);
		int fontSizePx = (int) (font.getSize() * 2.0 / 3);
		int paddingTop = (this.getHeight() - fontSizePx) / 2 + fontSizePx;
		int paddingLeft = (this.getWidth() - this.getText().getBytes().length
				* fontSizePx * 3 / 4) / 2;
		g.drawString(this.getText(), paddingLeft, paddingTop);
		
		paintNoise();
	}

	private void paintNoise() {
		for(ImageNoise noise:noises){
			noise.addNoise(this);
		}
	}

	/**
	 * 给图片添加噪声
	 * 
	 * @param noise
	 */
	public void addNoise(ImageNoise noise) {
		noises.add(noise);
	}

	/**
	 * 移除噪声
	 * 
	 * @param noise
	 */
	public void removeNoise(ImageNoise noise) {
		noises.remove(noise);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	/**
	 * 设置字体
	 * 
	 * @param font
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	public static void main(String[] args) throws IOException {
		VerificationImage image = new VerificationImage("56DS");
		image.addNoise(new GaussImageNoise(Color.BLACK,0.7));
		image.create();
		FileOutputStream fos = new FileOutputStream("2.jpg");
		ImageIO.write(image, "jpg", fos);
		fos.flush();
		fos.close();
		Desktop.getDesktop().open(new File("2.jpg"));
	}

}
