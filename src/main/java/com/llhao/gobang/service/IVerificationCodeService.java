package com.llhao.gobang.service;

import com.llhao.gobang.image.VerificationImage;

import redis.clients.jedis.Jedis;

/**
 * @author ÂÞºÆ
 *
 **/
public interface IVerificationCodeService {
	VerificationImage requestImage(Integer width, Integer heigth, Integer fontSize, String text,double p);
	
	boolean verifiCode(String key,String value,Jedis jedis);
}
