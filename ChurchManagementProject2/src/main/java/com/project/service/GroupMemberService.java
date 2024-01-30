package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.entity.GroupMember;
import com.project.entity.Groupp;
import com.project.repository.GroupMemberRepository;
import com.project.repository.GroupRepository;

@Service
public class GroupMemberService {

	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private GroupMemberRepository groupMemberRepository;

	public ResponseEntity<List<GroupMember>> createMembersForGroup(Long groupId, GroupMember groupMember) {
		Groupp group = groupRepository.findById(groupId).orElse(null);

		if (group == null) {
			return ResponseEntity.notFound().build();
		}
		groupMember.setGroupp(group);
		groupMemberRepository.save(groupMember);
		return ResponseEntity.status(HttpStatus.CREATED).body(group.getMembers());
	}

	public List<GroupMember> getAllGroupMembers() {
		return groupMemberRepository.findAll();
	}

	public ResponseEntity<GroupMember> getGroupMemberById(Long groupMemberId) {
		Optional<GroupMember> familyMember = groupMemberRepository.findById(groupMemberId);
		return familyMember.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<GroupMember> updateGroupMember(Long groupMemberId, GroupMember updatedGroupMember) {
		GroupMember existingGroupMember = groupMemberRepository.findById(groupMemberId).orElse(null);
		if (existingGroupMember != null) {
			if (updatedGroupMember.getName() != null) {
				existingGroupMember.setName(updatedGroupMember.getName());
			}
			if (updatedGroupMember.getContact() != null) {
				existingGroupMember.setContact(updatedGroupMember.getContact());
			}
			if (updatedGroupMember.getContact() != null) {
				existingGroupMember.setContact(updatedGroupMember.getContact());
			}
			if (updatedGroupMember.getEmail() != null) {
				existingGroupMember.setEmail(updatedGroupMember.getEmail());
			}
			
			return ResponseEntity.ok(groupMemberRepository.save(existingGroupMember));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<String> deleteGroupMember(Long groupMemberId) {
		Optional<GroupMember> groupMember = groupMemberRepository.findById(groupMemberId);
		if (groupMember.isPresent()) {
			groupMemberRepository.deleteById(groupMemberId);
			return ResponseEntity.ok("GroupMember with ID '" + groupMemberId + "' deleted successfully.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}