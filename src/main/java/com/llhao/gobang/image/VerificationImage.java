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
 * ��֤��ͼƬ�࣬���ڻ���һ����֤��ͼƬ
 * 
 * @author �޺�
 **/
public class VerificationImage extends BufferedImage {
	public static int WIDTH_DEFAULT = 200;
	public static int HEIGHT_DEFAULT = 75;
	public static Font FONT_DEFAULT = new Font("����", 0, 70);
	private Font font = FONT_DEFAULT;
	private String text;
	private List<ImageNoise> noises = new ArrayList<ImageNoise>();

	/**
	 * ����һ����֤��ͼƬ��ָ�����ȿ�Ⱥ���������
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
	 * ����һ����֤��ͼƬ������ΪĬ��ֵ������Ϊ�����ַ���
	 * 
	 * @param text
	 */
	public VerificationImage(String text) {
		this(WIDTH_DEFAULT, HEIGHT_DEFAULT, text);
	}

	/**
	 * ����ͼƬ
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
	 * ��ͼƬ�������
	 * 
	 * @param noise
	 */
	public void addNoise(ImageNoise noise) {
		noises.add(noise);
	}

	/**
	 * �Ƴ�����
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
	 * ��������
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
