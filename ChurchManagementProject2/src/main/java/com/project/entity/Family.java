package com.project.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Family {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "family_id")
	private long familyId;
	private String familyName;
	private String familyHeadName;
	private String contact;
	private String email;
	private String address;

	@OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
	private List<FamilyMember> members;

	@OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
	private List<FamilyEvent> events;

	public Family() {
	}

	public Family(long familyId, String familyName, String familyHeadName, String contact, String email, String address,
			List<FamilyMember> members, List<FamilyEvent> events) {
		super();
		this.familyId = familyId;
		this.familyName = familyName;
		this.familyHeadName = familyHeadName;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.members = members;
		this.events = events;
	}

	public long getFamilyId() {
		return familyId;
	}

	public void setFamilyId(long familyId) {
		this.familyId = familyId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFamilyHeadName() {
		return familyHeadName;
	}

	public void setFamilyHeadName(String familyHeadName) {
		this.familyHeadName = familyHeadName;
	}

	public List<FamilyMember> getMembers() {
		return members;
	}

	public void setMembers(List<FamilyMember> members) {
		this.members = members;
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

	public List<FamilyEvent> getEvents() {
		return events;
	}

	public void setEvents(List<FamilyEvent> events) {
		this.events = events;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
