package com.project.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;
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

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserEvent> events;

	public User() {

	}

	public User(String roleType, String firstName, String lastName, String contact, String email,
			String address, String password, int active, Role role, List<UserEvent> events) {
		super();
		this.roleType = roleType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.password = password;
		this.active = active;
		this.role = role;
		this.events = events;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public List<UserEvent> getEvents() {
		return events;
	}

	public void setEvents(List<UserEvent> events) {
		this.events = events;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	

}
