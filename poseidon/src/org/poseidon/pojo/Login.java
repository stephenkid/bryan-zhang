package org.poseidon.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Login entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LOGIN", schema = "POSEIDON", uniqueConstraints = @UniqueConstraint(columnNames = "LOGIN_EMAIL"))
public class Login implements java.io.Serializable {

	// Fields

	private Long loginId;
	private Login login;
	private String loginEmail;
	private String loginPassword;
	private String loginName;
	private String electronName;
	private String telephone;
	private String mobile;
	private String address;
	private String tax;
	private Long isAvail;
	private Date inputDate;
	private Date editDate;
	private Date auditDate;
	private String noticeMessage;
	private Set<LoginActor> loginActors = new HashSet<LoginActor>(0);
	private Set<Login> logins = new HashSet<Login>(0);

	// Constructors

	/** default constructor */
	public Login() {
	}

	/** minimal constructor */
	public Login(Long loginId, String loginEmail, String loginPassword,
			String loginName, Long isAvail, Date inputDate) {
		this.loginId = loginId;
		this.loginEmail = loginEmail;
		this.loginPassword = loginPassword;
		this.loginName = loginName;
		this.isAvail = isAvail;
		this.inputDate = inputDate;
	}

	/** full constructor */
	public Login(Long loginId, Login login, String loginEmail,
			String loginPassword, String loginName, String electronName,
			String telephone, String mobile, String address, String tax,
			Long isAvail, Date inputDate, Date editDate, Date auditDate,
			String noticeMessage, Set<LoginActor> loginActors, Set<Login> logins) {
		this.loginId = loginId;
		this.login = login;
		this.loginEmail = loginEmail;
		this.loginPassword = loginPassword;
		this.loginName = loginName;
		this.electronName = electronName;
		this.telephone = telephone;
		this.mobile = mobile;
		this.address = address;
		this.tax = tax;
		this.isAvail = isAvail;
		this.inputDate = inputDate;
		this.editDate = editDate;
		this.auditDate = auditDate;
		this.noticeMessage = noticeMessage;
		this.loginActors = loginActors;
		this.logins = logins;
	}

	// Property accessors
	@Id
	@Column(name = "LOGIN_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getLoginId() {
		return this.loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUDIT_LOGIN_ID")
	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Column(name = "LOGIN_EMAIL", unique = true, nullable = false, length = 40)
	public String getLoginEmail() {
		return this.loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	@Column(name = "LOGIN_PASSWORD", nullable = false, length = 100)
	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Column(name = "LOGIN_NAME", nullable = false, length = 40)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "ELECTRON_NAME")
	public String getElectronName() {
		return this.electronName;
	}

	public void setElectronName(String electronName) {
		this.electronName = electronName;
	}

	@Column(name = "TELEPHONE", length = 40)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "MOBILE", length = 40)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "ADDRESS", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TAX", length = 40)
	public String getTax() {
		return this.tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	@Column(name = "IS_AVAIL", nullable = false, precision = 22, scale = 0)
	public Long getIsAvail() {
		return this.isAvail;
	}

	public void setIsAvail(Long isAvail) {
		this.isAvail = isAvail;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INPUT_DATE", nullable = false, length = 7)
	public Date getInputDate() {
		return this.inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EDIT_DATE", length = 7)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AUDIT_DATE", length = 7)
	public Date getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	@Column(name = "NOTICE_MESSAGE", length = 200)
	public String getNoticeMessage() {
		return this.noticeMessage;
	}

	public void setNoticeMessage(String noticeMessage) {
		this.noticeMessage = noticeMessage;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "login")
	public Set<LoginActor> getLoginActors() {
		return this.loginActors;
	}

	public void setLoginActors(Set<LoginActor> loginActors) {
		this.loginActors = loginActors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "login")
	public Set<Login> getLogins() {
		return this.logins;
	}

	public void setLogins(Set<Login> logins) {
		this.logins = logins;
	}

}