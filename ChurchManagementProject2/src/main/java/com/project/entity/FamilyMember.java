package com.project.entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FamilyMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long familyMemberId;
	private String name;
	private LocalDate dateOfBirth;
	private String contact;
	private String relation;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Family family;

	public FamilyMember() {
	}

	public FamilyMember(String name, LocalDate dateOfBirth, String contact, String relation, Family family) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.contact = contact;
		this.relation = relation;
		this.family = family;
	}

	public long getFamilyMemberId() {
		return familyMemberId;
	}

	public void setFamilyMemberId(long familyMemberId) {
		this.familyMemberId = familyMemberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}
}