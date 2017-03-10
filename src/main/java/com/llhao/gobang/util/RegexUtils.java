package com.llhao.gobang.util;

/**
 * 正则表达式工具类，用于处理一些常见的正则表达式任务
 * 
 * @author 罗浩
 **/
public class RegexUtils {
	/**
	 * 验证一个字符串是否为邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmailAdress(String email) {
		return email != null && email.matches("\\w+@(\\w+)+(.\\w+)*");
	}

	/**
	 * 判断一个字符串是否为手机号
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		return phone != null && phone.matches("1[3-8]\\d{9}");
	}

}
