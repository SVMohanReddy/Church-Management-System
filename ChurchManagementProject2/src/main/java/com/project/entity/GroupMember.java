package com.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GroupMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long GroupMemberId;
	private String name;
	private String contact;
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Groupp groupp;

	public GroupMember() {
	}

	public GroupMember(long groupMemberId, String name, String contact, String email, Groupp groupp) {
		super();
		GroupMemberId = groupMemberId;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.groupp = groupp;
	}

	public long getGroupMemberId() {
		return GroupMemberId;
	}

	public void setGroupMemberId(long groupMemberId) {
		GroupMemberId = groupMemberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Groupp getGroupp() {
		return groupp;
	}

	public void setGroupp(Groupp groupp) {
		this.groupp = groupp;
	}

	
}