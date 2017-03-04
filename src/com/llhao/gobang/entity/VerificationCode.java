package com.llhao.gobang.entity;

/**
 * @author �޺�
 * 
 **/
public class VerificationCode {
	private String key;
	private String value;
	private boolean used = false;
	private long createTime;
	private long invalidTime = 60*1000;

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(long invalidTime) {
		this.invalidTime = invalidTime;
	}

	public VerificationCode(String key, String value) {
		super();
		this.key = key;
		this.value = value;
		this.createTime = System.currentTimeMillis();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	/**
	 * ��֤һ����֤���Ƿ���ȷ
	 * 
	 * @param value
	 * @return
	 */
	public boolean check(String value) {
		if (System.currentTimeMillis() - createTime > invalidTime) {
			throw new RuntimeException("��֤���Ѿ�ʧЧ");
		} else if (isUsed()) {
			throw new RuntimeException("��֤���Ѿ�ʹ��");
		} else if (this.value.equals(value)) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		VerificationCode code = new VerificationCode("32", "342");
		code.check("342");
	}
}
