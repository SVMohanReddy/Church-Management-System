package com.project.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Ministry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ministry_id")
	private long ministryId;
	private String roleType;
	private String firstName;
	private String lastName;
	private String contact;
	private String email;
	private String address;
	private String password;
	private int active;

	@OneToOne(cascade = CascadeType.ALL)
	private Role role;

	public Ministry() {
	}

	public Ministry(String roleType, String firstName, String lastName, String contact, String email, String address,
			String password, int active) {
		super();
		this.roleType = roleType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.password = password;
		this.active = active;
	}

	public long getMinistryId() {
		return ministryId;
	}

	public void setMinistryId(long ministryId) {
		this.ministryId = ministryId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
