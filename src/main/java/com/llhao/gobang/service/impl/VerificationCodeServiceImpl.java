package com.llhao.gobang.service.impl;

import java.awt.Font;

import org.springframework.stereotype.Service;

import com.llhao.gobang.image.GaussImageNoise;
import com.llhao.gobang.image.VerificationImage;
import com.llhao.gobang.service.IVerificationCodeService;

import redis.clients.jedis.Jedis;

/**
 * @author 罗浩
 *
 **/
@Service
public class VerificationCodeServiceImpl implements IVerificationCodeService {

	public VerificationImage requestImage(Integer width, Integer height, Integer fontSize, String text, double p) {
		width = width == null ? VerificationImage.WIDTH_DEFAULT : width;
		height = height == null ? VerificationImage.HEIGHT_DEFAULT : height;
		fontSize = fontSize == null ? VerificationImage.FONT_DEFAULT.getSize() : fontSize;
		VerificationImage image = new VerificationImage(width, height, text);
		image.setFont(new Font("宋体", 0, fontSize));
		image.addNoise(new GaussImageNoise(p));
		image.create();
		return image;
	}

	public boolean verifiCode(String key, String value, Jedis jedis) {
		String realValue = jedis.get(key);
		if (realValue == null || realValue.isEmpty()) {
			throw new RuntimeException("验证码失效");
		}
		if (realValue.equals(value)) {
			return false;
		}
		return true;
	}

}
