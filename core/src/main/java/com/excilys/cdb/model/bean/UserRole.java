package com.excilys.cdb.model.bean;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
@Entity
@Table(name = "user_roles", 
	uniqueConstraints = @UniqueConstraint(
		columnNames = { "role", "id_user" }))
public class UserRole{
 
	private Integer id;
	private User user;
	private String role;
 
	public UserRole() {
	}
 
	public UserRole(User user, String role) {
		this.user = user;
		this.role = role;
	}
 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", 
		unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable = false)
	public User getUser() {
		return this.user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}
 
	@Column(name = "role", nullable = false, length = 45)
	public String getRole() {
		return this.role;
	}
 
	public void setRole(String role) {
		this.role = role;
	}
 
}