package org.poseidon.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LoginActor entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LOGIN_ACTOR", schema = "POSEIDON")
public class LoginActor implements java.io.Serializable {

	// Fields

	private Long loginActorId;
	private Login login;
	private Actor actor;

	// Constructors

	/** default constructor */
	public LoginActor() {
	}

	/** full constructor */
	public LoginActor(Long loginActorId, Login login, Actor actor) {
		this.loginActorId = loginActorId;
		this.login = login;
		this.actor = actor;
	}

	// Property accessors
	@Id
	@Column(name = "LOGIN_ACTOR_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getLoginActorId() {
		return this.loginActorId;
	}

	public void setLoginActorId(Long loginActorId) {
		this.loginActorId = loginActorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOGIN_ID", nullable = false)
	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTOR_ID", nullable = false)
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}