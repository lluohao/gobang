package com.llhao.gobang.entity;

/**
 * @author 罗浩
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
	 * 验证一条验证码是否正确
	 * 
	 * @param value
	 * @return
	 */
	public boolean check(String value) {
		if (System.currentTimeMillis() - createTime > invalidTime) {
			throw new RuntimeException("验证码已经失效");
		} else if (isUsed()) {
			throw new RuntimeException("验证码已经使用");
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
