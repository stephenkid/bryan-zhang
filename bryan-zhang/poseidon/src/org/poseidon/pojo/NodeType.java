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
 * NodeType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NODE_TYPE", schema = "POSEIDON")
public class NodeType implements java.io.Serializable {

	// Fields

	private Long nodeTypeId;
	private String nodeTypeName;
	private String nodeTypeMemo;
	private Set<Node> nodes = new HashSet<Node>(0);

	// Constructors

	/** default constructor */
	public NodeType() {
	}

	/** minimal constructor */
	public NodeType(Long nodeTypeId, String nodeTypeName) {
		this.nodeTypeId = nodeTypeId;
		this.nodeTypeName = nodeTypeName;
	}

	/** full constructor */
	public NodeType(Long nodeTypeId, String nodeTypeName, String nodeTypeMemo,
			Set<Node> nodes) {
		this.nodeTypeId = nodeTypeId;
		this.nodeTypeName = nodeTypeName;
		this.nodeTypeMemo = nodeTypeMemo;
		this.nodes = nodes;
	}

	// Property accessors
	@Id
	@Column(name = "NODE_TYPE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getNodeTypeId() {
		return this.nodeTypeId;
	}

	public void setNodeTypeId(Long nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
	}

	@Column(name = "NODE_TYPE_NAME", nullable = false, length = 40)
	public String getNodeTypeName() {
		return this.nodeTypeName;
	}

	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
	}

	@Column(name = "NODE_TYPE_MEMO", length = 200)
	public String getNodeTypeMemo() {
		return this.nodeTypeMemo;
	}

	public void setNodeTypeMemo(String nodeTypeMemo) {
		this.nodeTypeMemo = nodeTypeMemo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nodeType")
	public Set<Node> getNodes() {
		return this.nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

}