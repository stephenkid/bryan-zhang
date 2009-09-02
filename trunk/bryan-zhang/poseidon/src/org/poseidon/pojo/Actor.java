package org.poseidon.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Actor entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACTOR", schema = "POSEIDON")
public class Actor implements java.io.Serializable {

	// Fields

	private Long actorId;
	private String actorName;
	private String actorMemo;
	private Long isAdminActor;
	private Long isReceiveEmail;
	private String isValid;
	private Set<ActorMenu> actorMenus = new HashSet<ActorMenu>(0);
	private Set<LoginActor> loginActors = new HashSet<LoginActor>(0);

	// Constructors

	/** default constructor */
	public Actor() {
	}

	/** minimal constructor */
	public Actor(Long actorId, String actorName, Long isAdminActor,
			Long isReceiveEmail) {
		this.actorId = actorId;
		this.actorName = actorName;
		this.isAdminActor = isAdminActor;
		this.isReceiveEmail = isReceiveEmail;
	}

	/** full constructor */
	public Actor(Long actorId, String actorName, String actorMemo,
			Long isAdminActor, Long isReceiveEmail, String isValid,
			Set<ActorMenu> actorMenus, Set<LoginActor> loginActors) {
		this.actorId = actorId;
		this.actorName = actorName;
		this.actorMemo = actorMemo;
		this.isAdminActor = isAdminActor;
		this.isReceiveEmail = isReceiveEmail;
		this.isValid = isValid;
		this.actorMenus = actorMenus;
		this.loginActors = loginActors;
	}

	// Property accessors
	@Id
	@Column(name = "ACTOR_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getActorId() {
		return this.actorId;
	}

	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}

	@Column(name = "ACTOR_NAME", nullable = false, length = 40)
	public String getActorName() {
		return this.actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	@Column(name = "ACTOR_MEMO", length = 200)
	public String getActorMemo() {
		return this.actorMemo;
	}

	public void setActorMemo(String actorMemo) {
		this.actorMemo = actorMemo;
	}

	@Column(name = "IS_ADMIN_ACTOR", nullable = false, precision = 22, scale = 0)
	public Long getIsAdminActor() {
		return this.isAdminActor;
	}

	public void setIsAdminActor(Long isAdminActor) {
		this.isAdminActor = isAdminActor;
	}

	@Column(name = "IS_RECEIVE_EMAIL", nullable = false, precision = 22, scale = 0)
	public Long getIsReceiveEmail() {
		return this.isReceiveEmail;
	}

	public void setIsReceiveEmail(Long isReceiveEmail) {
		this.isReceiveEmail = isReceiveEmail;
	}

	@Column(name = "IS_VALID", length = 1)
	public String getIsValid() {
		return this.isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actor")
	public Set<ActorMenu> getActorMenus() {
		return this.actorMenus;
	}

	public void setActorMenus(Set<ActorMenu> actorMenus) {
		this.actorMenus = actorMenus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actor")
	public Set<LoginActor> getLoginActors() {
		return this.loginActors;
	}

	public void setLoginActors(Set<LoginActor> loginActors) {
		this.loginActors = loginActors;
	}

}