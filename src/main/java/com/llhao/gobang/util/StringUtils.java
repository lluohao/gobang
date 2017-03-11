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

	public static int realLength(String text) {
		int len = text.getBytes().length;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(0);
			len += c >= 'A' && c <= 'Z' ? 1 : 0;
		}
		return len;
	}
}
