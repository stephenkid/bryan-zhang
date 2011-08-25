package com.myNutz.pojo;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_person")
public class Person implements Serializable {

	private static final long serialVersionUID = -3533037865006314370L;
	
	@Id
	@Column("id")
	private Long id;
	
	@Column("name")
	private String name;
	
	@Column("age")
	private Integer age;
	
	@Column("email")
	private String email;
	
	@Column("is_valid")
	private Boolean isValid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
}
