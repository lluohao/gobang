package com.llhao.gobang.entity;

import java.sql.Timestamp;

/**
 * Record entity. @author MyEclipse Persistence Tools
 */

public class Record implements java.io.Serializable {

	// Fields

	private Integer id;
	private User userByWhite;
	private User userByBlack;
	private Timestamp time;
	private String file;
	private Integer win;

	// Constructors

	/** default constructor */
	public Record() {
	}

	/** full constructor */
	public Record(User userByWhite, User userByBlack, Timestamp time,
			String file, Integer win) {
		this.userByWhite = userByWhite;
		this.userByBlack = userByBlack;
		this.time = time;
		this.file = file;
		this.win = win;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserByWhite() {
		return this.userByWhite;
	}

	public void setUserByWhite(User userByWhite) {
		this.userByWhite = userByWhite;
	}

	public User getUserByBlack() {
		return this.userByBlack;
	}

	public void setUserByBlack(User userByBlack) {
		this.userByBlack = userByBlack;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getWin() {
		return this.win;
	}

	public void setWin(Integer win) {
		this.win = win;
	}

}