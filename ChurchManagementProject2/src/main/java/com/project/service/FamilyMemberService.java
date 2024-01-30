package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.entity.Family;
import com.project.entity.FamilyMember;
import com.project.repository.FamilyMemberRepository;
import com.project.repository.FamilyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyMemberService {

	@Autowired
	private FamilyRepository familyRepository;
	@Autowired
	private FamilyMemberRepository familyMemberRepository;

	public ResponseEntity<List<FamilyMember>> createMembersForFamily(Long familyId, FamilyMember familyMember) {
		Family family = familyRepository.findById(familyId).orElse(null);

		if (family == null) {
			return ResponseEntity.notFound().build();
		}
		familyMember.setFamily(family);
		familyMemberRepository.save(familyMember);
		return ResponseEntity.status(HttpStatus.CREATED).body(family.getMembers());
	}

	public List<FamilyMember> getAllFamilyMembers() {
		return familyMemberRepository.findAll();
	}

	public ResponseEntity<FamilyMember> getFamilyMemberById(Long familyMemberId) {
		Optional<FamilyMember> familyMember = familyMemberRepository.findById(familyMemberId);
		return familyMember.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<FamilyMember> updateFamilyMember(Long familyMemberId, FamilyMember updatedFamilyMember) {
		FamilyMember existingFamilyMember = familyMemberRepository.findById(familyMemberId).orElse(null);
		if (existingFamilyMember != null) {
			if (updatedFamilyMember.getName() != null) {
				existingFamilyMember.setName(updatedFamilyMember.getName());
			}
			if (updatedFamilyMember.getDateOfBirth() != null) {
				existingFamilyMember.setDateOfBirth(updatedFamilyMember.getDateOfBirth());
			}
			if (updatedFamilyMember.getContact() != null) {
				existingFamilyMember.setContact(updatedFamilyMember.getContact());
			}
			if (updatedFamilyMember.getRelation() != null) {
				existingFamilyMember.setRelation(updatedFamilyMember.getRelation());
			}
			if (updatedFamilyMember.getFamily() != null) {
				existingFamilyMember.setFamily(updatedFamilyMember.getFamily());
			}
			return ResponseEntity.ok(familyMemberRepository.save(existingFamilyMember));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<String> deleteFamilyMember(Long familyMemberId) {
		Optional<FamilyMember> familyMember = familyMemberRepository.findById(familyMemberId);
		if (familyMember.isPresent()) {
			familyMemberRepository.deleteById(familyMemberId);
			return ResponseEntity.ok("Family Member with ID '" + familyMemberId + "' deleted successfully.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}