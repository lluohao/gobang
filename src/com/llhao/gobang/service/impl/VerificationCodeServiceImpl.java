package com.llhao.gobang.service.impl;

import org.springframework.stereotype.Service;

import com.llhao.gobang.entity.VerificationCode;
import com.llhao.gobang.service.IVerificationCodeService;
import com.llhao.gobang.util.StringUtils;

/**
 * @author ÂÞºÆ
 * 
 **/
@Service(value="verificationCodeService")
public class VerificationCodeServiceImpl implements IVerificationCodeService{
	@Override
	public VerificationCode requestVerificationCode(String key) {
		String value = StringUtils.randString(4);
		return new VerificationCode(key, value);
	}
}
