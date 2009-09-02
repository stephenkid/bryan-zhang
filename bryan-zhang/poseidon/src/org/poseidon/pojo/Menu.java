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
 * Menu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MENU", schema = "POSEIDON", uniqueConstraints = @UniqueConstraint(columnNames = "MENU_CODE"))
public class Menu implements java.io.Serializable {

	// Fields

	private Long menuId;
	private Menu menu;
	private Long hasChild;
	private Long menuDepth;
	private String menuCode;
	private String menuName;
	private String menuMemo;
	private String menuAction;
	private String navigationDesc;
	private Long isAvail;
	private Date inputDate;
	private Long inputLogin;
	private Date editDate;
	private Long editLogin;
	private Long displaySequence;
	private Set<Menu> menus = new HashSet<Menu>(0);
	private Set<ActorMenu> actorMenus = new HashSet<ActorMenu>(0);

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(Long menuId, Long hasChild, Long menuDepth, String menuCode,
			String menuName, Long isAvail, Date inputDate) {
		this.menuId = menuId;
		this.hasChild = hasChild;
		this.menuDepth = menuDepth;
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.isAvail = isAvail;
		this.inputDate = inputDate;
	}

	/** full constructor */
	public Menu(Long menuId, Menu menu, Long hasChild, Long menuDepth,
			String menuCode, String menuName, String menuMemo,
			String menuAction, String navigationDesc, Long isAvail,
			Date inputDate, Long inputLogin, Date editDate, Long editLogin,
			Long displaySequence, Set<Menu> menus, Set<ActorMenu> actorMenus) {
		this.menuId = menuId;
		this.menu = menu;
		this.hasChild = hasChild;
		this.menuDepth = menuDepth;
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuMemo = menuMemo;
		this.menuAction = menuAction;
		this.navigationDesc = navigationDesc;
		this.isAvail = isAvail;
		this.inputDate = inputDate;
		this.inputLogin = inputLogin;
		this.editDate = editDate;
		this.editLogin = editLogin;
		this.displaySequence = displaySequence;
		this.menus = menus;
		this.actorMenus = actorMenus;
	}

	// Property accessors
	@Id
	@Column(name = "MENU_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name = "HAS_CHILD", nullable = false, precision = 22, scale = 0)
	public Long getHasChild() {
		return this.hasChild;
	}

	public void setHasChild(Long hasChild) {
		this.hasChild = hasChild;
	}

	@Column(name = "MENU_DEPTH", nullable = false, precision = 22, scale = 0)
	public Long getMenuDepth() {
		return this.menuDepth;
	}

	public void setMenuDepth(Long menuDepth) {
		this.menuDepth = menuDepth;
	}

	@Column(name = "MENU_CODE", unique = true, nullable = false, length = 20)
	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Column(name = "MENU_NAME", nullable = false, length = 40)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "MENU_MEMO", length = 200)
	public String getMenuMemo() {
		return this.menuMemo;
	}

	public void setMenuMemo(String menuMemo) {
		this.menuMemo = menuMemo;
	}

	@Column(name = "MENU_ACTION", length = 100)
	public String getMenuAction() {
		return this.menuAction;
	}

	public void setMenuAction(String menuAction) {
		this.menuAction = menuAction;
	}

	@Column(name = "NAVIGATION_DESC", length = 100)
	public String getNavigationDesc() {
		return this.navigationDesc;
	}

	public void setNavigationDesc(String navigationDesc) {
		this.navigationDesc = navigationDesc;
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

	@Column(name = "INPUT_LOGIN", precision = 22, scale = 0)
	public Long getInputLogin() {
		return this.inputLogin;
	}

	public void setInputLogin(Long inputLogin) {
		this.inputLogin = inputLogin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EDIT_DATE", length = 7)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@Column(name = "EDIT_LOGIN", precision = 22, scale = 0)
	public Long getEditLogin() {
		return this.editLogin;
	}

	public void setEditLogin(Long editLogin) {
		this.editLogin = editLogin;
	}

	@Column(name = "DISPLAY_SEQUENCE", precision = 22, scale = 0)
	public Long getDisplaySequence() {
		return this.displaySequence;
	}

	public void setDisplaySequence(Long displaySequence) {
		this.displaySequence = displaySequence;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<ActorMenu> getActorMenus() {
		return this.actorMenus;
	}

	public void setActorMenus(Set<ActorMenu> actorMenus) {
		this.actorMenus = actorMenus;
	}

}