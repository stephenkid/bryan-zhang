package com.hermes.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user", catalog = "hermes")
public class User implements java.io.Serializable {

	// Fields

	private Long userId;
	private String userName;
	private String password;
	private Integer isValid;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String password, Integer isValid) {
		this.userName = userName;
		this.password = password;
		this.isValid = isValid;
	}

	/** full constructor */
	public User(String userName, String password, Integer isValid,
			Timestamp updateTime) {
		this.userName = userName;
		this.password = password;
		this.isValid = isValid;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "user_name", nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "is_valid", nullable = false)
	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	@Column(name = "update_time", length = 19)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}