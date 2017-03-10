package com.llhao.gobang.util;


/**
 * @author �޺�
 * 
 **/
public class StringUtils {
	/**
	 * ��Сд��ĸ����
	 */
	public static final String BASE_WORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * ����һ������ַ���
	 * 
	 * @param length
	 *            �ַ����ĳ���
	 * @param base
	 *            �ַ���
	 * @return
	 */
	public static String randString(int length, String base) {
		char[] cs = new char[length];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = base.charAt((int) (Math.random() * base.length()));
		}
		return new String(cs);
	}

	public static String randString(int length) {
		return randString(length, BASE_WORD);
	}
}
