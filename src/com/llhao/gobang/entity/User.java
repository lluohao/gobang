package com.llhao.gobang.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", email=" + email + ", image=" + image + ", time=" + time
				+ ", win=" + win + ", tie=" + tie + ", lose=" + lose
				+ "]";
	}

	private Integer id;
	private String name;
	private String password;
	private String email;
	private String image;
	private Date time;
	private Integer win;
	private Integer tie;
	private Integer lose;
	private Set recordsForWhite = new HashSet(0);
	private Set recordsForBlack = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String name, String password, String email, Date time,
			Integer win, Integer tie, Integer lose) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.time = time;
		this.win = win;
		this.tie = tie;
		this.lose = lose;
	}

	/** full constructor */
	public User(String name, String password, String email, String image,
			Date time, Integer win, Integer tie, Integer lose,
			Set recordsForWhite, Set recordsForBlack) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.image = image;
		this.time = time;
		this.win = win;
		this.tie = tie;
		this.lose = lose;
		this.recordsForWhite = recordsForWhite;
		this.recordsForBlack = recordsForBlack;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getWin() {
		return this.win;
	}

	public void setWin(Integer win) {
		this.win = win;
	}

	public Integer getTie() {
		return this.tie;
	}

	public void setTie(Integer tie) {
		this.tie = tie;
	}

	public Integer getLose() {
		return this.lose;
	}

	public void setLose(Integer lose) {
		this.lose = lose;
	}

	public Set getRecordsForWhite() {
		return this.recordsForWhite;
	}

	public void setRecordsForWhite(Set recordsForWhite) {
		this.recordsForWhite = recordsForWhite;
	}

	public Set getRecordsForBlack() {
		return this.recordsForBlack;
	}

	public void setRecordsForBlack(Set recordsForBlack) {
		this.recordsForBlack = recordsForBlack;
	}

}