package com.project.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Groupp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long groupId;
	private String groupType;
	private String groupName;
	private String groupLeader;
	private String description;

	@OneToMany(mappedBy = "groupp", cascade = CascadeType.ALL)
	private List<GroupEvent> events;
	
	@OneToMany(mappedBy = "groupp", cascade = CascadeType.ALL)
	private List<GroupMember> members;

	public Groupp() {
	}

	public Groupp(String groupType, String groupName, String groupLeader, String description) {
		super();
		this.groupType = groupType;
		this.groupName = groupName;
		this.groupLeader = groupLeader;
		this.description = description;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<GroupEvent> getEvents() {
		return events;
	}

	public void setEvents(List<GroupEvent> events) {
		this.events = events;
	}

	public List<GroupMember> getMembers() {
		return members;
	}

	public void setMembers(List<GroupMember> members) {
		this.members = members;
	}

}
