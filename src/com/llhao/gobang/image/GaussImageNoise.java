package com.llhao.gobang.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * @author ÂÞºÆ
 * 
 **/
public class GaussImageNoise implements ImageNoise {

	private Color noiseColor = Color.BLACK;
	private double p = 0.7;

	public GaussImageNoise() {
	}

	public GaussImageNoise(Color noiseColor) {
		super();
		this.noiseColor = noiseColor;
	}

	public GaussImageNoise(double p) {
		super();
		this.p = p;
	}

	public GaussImageNoise(Color noiseColor, double p) {
		super();
		this.noiseColor = noiseColor;
		this.p = p;
	}

	@Override
	public void addNoise(BufferedImage image) {
		int width = image.getWidth();
		int heigth = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < heigth; j++) {
				if (Math.random() < p) {
					image.setRGB(i, j, noiseColor.getRGB());
				}
			}
		}
	}

	public void setNoiseColor(Color noiseColor) {
		this.noiseColor = noiseColor;
	}

	public Color getNoiseColor() {
		return noiseColor;
	}
}
