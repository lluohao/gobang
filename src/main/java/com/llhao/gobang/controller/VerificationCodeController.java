package com.llhao.gobang.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.llhao.gobang.service.IVerificationCodeService;
import com.llhao.gobang.util.StringUtils;

import redis.clients.jedis.Jedis;

/**
 * @author ÂÞºÆ
 *
 **/
@Controller
public class VerificationCodeController {
	@Autowired
	private IVerificationCodeService verificationCodeService;
	@Autowired
	private Jedis jedis;

	@RequestMapping("/vcode")
	public void vcode(Integer width, Integer height, Integer fontSize, @RequestParam String key,
			HttpServletResponse res) {
		String value = jedis.get(key);
		BufferedImage image = null;
		if (value != null) {
			image = verificationCodeService.requestImage(width, height, fontSize, "ÇëË¢ÐÂ", 0);
		} else {
			String text = StringUtils.randString(4);
			jedis.set(key, text, "NX", "EX", 60);
			image = verificationCodeService.requestImage(width, height, fontSize, text, 0.7);
		}
		res.setContentType("image/jpg");
		try {
			ImageIO.write(image, "jpg", res.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/vericode")
	public String veriCode(@RequestParam String key, @RequestParam String value) {
		try {
			verificationCodeService.verifiCode(key, value, jedis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}
}
