package org.poseidon.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TLogin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_login", catalog = "poseidon")
public class Login implements Serializable {

	private static final long serialVersionUID = -388009660243783191L;
	
	private Long loginId;
	private String loginEmail;
	private String loginPassword;
	private String loginName;
	private String telephone;
	private String mobile;
	private String address;
	private String tax;
	private Boolean isAvail;
	private Date inputDate;
	private Date editDate;
	private String memo;

	// Constructors

	/** default constructor */
	public Login() {
	}

	/** minimal constructor */
	public Login(String loginEmail, String loginPassword, String loginName) {
		this.loginEmail = loginEmail;
		this.loginPassword = loginPassword;
		this.loginName = loginName;
	}

	/** full constructor */
	public Login(String loginEmail, String loginPassword, String loginName,
			String telephone, String mobile, String address, String tax,
			Boolean isAvail, Date inputDate, Date editDate, String memo) {
		this.loginEmail = loginEmail;
		this.loginPassword = loginPassword;
		this.loginName = loginName;
		this.telephone = telephone;
		this.mobile = mobile;
		this.address = address;
		this.tax = tax;
		this.isAvail = isAvail;
		this.inputDate = inputDate;
		this.editDate = editDate;
		this.memo = memo;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "login_id", unique = true, nullable = false)
	public Long getLoginId() {
		return this.loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	@Column(name = "login_email", nullable = false, length = 40)
	public String getLoginEmail() {
		return this.loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	@Column(name = "login_password", nullable = false, length = 100)
	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Column(name = "login_name", nullable = false, length = 40)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "telephone", length = 40)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "mobile", length = 40)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "tax", length = 40)
	public String getTax() {
		return this.tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	@Column(name = "is_avail")
	public Boolean getIsAvail() {
		return this.isAvail;
	}

	public void setIsAvail(Boolean isAvail) {
		this.isAvail = isAvail;
	}

	@Column(name = "input_date", length = 10)
	public Date getInputDate() {
		return this.inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Column(name = "edit_date", length = 10)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@Column(name = "memo", length = 100)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}