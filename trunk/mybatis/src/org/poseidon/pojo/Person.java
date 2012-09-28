package org.poseidon.pojo;

import java.io.Serializable;
import java.util.Date;


public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
	private String name;
	private Integer age;
	private String address;
	private String mobile;
	private String email;
	private String company;
	private String title;
	private Date createTime;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    @Override
    public String toString() {
        return "Person [address=" + address + ", age=" + age + ", company=" + company + ", createTime=" + createTime + ", email=" + email + ", id=" + id + ", mobile=" + mobile + ", name=" + name + ", title=" + title + "]";
    }

}