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
 * Node entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NODE", schema = "POSEIDON", uniqueConstraints = @UniqueConstraint(columnNames = "NODE_CODE"))
public class Node implements java.io.Serializable {

	// Fields

	private Long nodeId;
	private Node node;
	private NodeType nodeType;
	private Long hasChild;
	private Long nodeDepth;
	private String nodeCode;
	private String nodeName;
	private String nodeMemo;
	private String contactPerson;
	private String telephone;
	private String mobile;
	private String address;
	private String tax;
	private String email;
	private Long isAvail;
	private Date inputDate;
	private Long inputLogin;
	private Date editDate;
	private Long editLogin;
	private Set<Node> nodes = new HashSet<Node>(0);

	// Constructors

	/** default constructor */
	public Node() {
	}

	/** minimal constructor */
	public Node(Long nodeId, NodeType nodeType, Long hasChild, Long nodeDepth,
			String nodeCode, String nodeName, Long isAvail, Date inputDate) {
		this.nodeId = nodeId;
		this.nodeType = nodeType;
		this.hasChild = hasChild;
		this.nodeDepth = nodeDepth;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.isAvail = isAvail;
		this.inputDate = inputDate;
	}

	/** full constructor */
	public Node(Long nodeId, Node node, NodeType nodeType, Long hasChild,
			Long nodeDepth, String nodeCode, String nodeName, String nodeMemo,
			String contactPerson, String telephone, String mobile,
			String address, String tax, String email, Long isAvail,
			Date inputDate, Long inputLogin, Date editDate, Long editLogin,
			Set<Node> nodes) {
		this.nodeId = nodeId;
		this.node = node;
		this.nodeType = nodeType;
		this.hasChild = hasChild;
		this.nodeDepth = nodeDepth;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeMemo = nodeMemo;
		this.contactPerson = contactPerson;
		this.telephone = telephone;
		this.mobile = mobile;
		this.address = address;
		this.tax = tax;
		this.email = email;
		this.isAvail = isAvail;
		this.inputDate = inputDate;
		this.inputLogin = inputLogin;
		this.editDate = editDate;
		this.editLogin = editLogin;
		this.nodes = nodes;
	}

	// Property accessors
	@Id
	@Column(name = "NODE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public Node getNode() {
		return this.node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NODE_TYPE_ID", nullable = false)
	public NodeType getNodeType() {
		return this.nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	@Column(name = "HAS_CHILD", nullable = false, precision = 22, scale = 0)
	public Long getHasChild() {
		return this.hasChild;
	}

	public void setHasChild(Long hasChild) {
		this.hasChild = hasChild;
	}

	@Column(name = "NODE_DEPTH", nullable = false, precision = 22, scale = 0)
	public Long getNodeDepth() {
		return this.nodeDepth;
	}

	public void setNodeDepth(Long nodeDepth) {
		this.nodeDepth = nodeDepth;
	}

	@Column(name = "NODE_CODE", unique = true, nullable = false, length = 20)
	public String getNodeCode() {
		return this.nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	@Column(name = "NODE_NAME", nullable = false, length = 40)
	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Column(name = "NODE_MEMO", length = 200)
	public String getNodeMemo() {
		return this.nodeMemo;
	}

	public void setNodeMemo(String nodeMemo) {
		this.nodeMemo = nodeMemo;
	}

	@Column(name = "CONTACT_PERSON", length = 40)
	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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

	@Column(name = "EMAIL", length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "node")
	public Set<Node> getNodes() {
		return this.nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

}