package com.llhao.gobang.util;

/**
 * ������ʽ�����࣬���ڴ���һЩ������������ʽ����
 * 
 * @author �޺�
 **/
public class RegexUtils {
	/**
	 * ��֤һ���ַ����Ƿ�Ϊ�ʼ���ַ
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmailAdress(String email) {
		return email != null && email.matches("\\w+@(\\w+)+(.\\w+)*");
	}

	/**
	 * �ж�һ���ַ����Ƿ�Ϊ�ֻ���
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		return phone != null && phone.matches("1[3-8]\\d{9}");
	}

}
