package com.llhao.gobang.service;

import com.llhao.gobang.entity.VerificationCode;

/**
 * @author ÂÞºÆ
 * 
 **/
public interface IVerificationCodeService {
	public VerificationCode requestVerificationCode(String key);
}