package org.poseidon.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ActorMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACTOR_MENU", schema = "POSEIDON")
public class ActorMenu implements java.io.Serializable {

	// Fields

	private Long actorMenuId;
	private Actor actor;
	private Menu menu;

	// Constructors

	/** default constructor */
	public ActorMenu() {
	}

	/** full constructor */
	public ActorMenu(Long actorMenuId, Actor actor, Menu menu) {
		this.actorMenuId = actorMenuId;
		this.actor = actor;
		this.menu = menu;
	}

	// Property accessors
	@Id
	@Column(name = "ACTOR_MENU_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getActorMenuId() {
		return this.actorMenuId;
	}

	public void setActorMenuId(Long actorMenuId) {
		this.actorMenuId = actorMenuId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTOR_ID", nullable = false)
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU_ID", nullable = false)
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}