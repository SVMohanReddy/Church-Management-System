package com.project.controller.membercontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.entity.FamilyMember;
import com.project.service.FamilyMemberService;

@RestController
@RequestMapping("/familyMember")
public class FamilyMemberController {

	@Autowired
	private FamilyMemberService familyMemberService;

	@PostMapping("/create/{familyId}")
	public ResponseEntity<Object> createMembersforFamily(@PathVariable Long familyId,
			@RequestBody FamilyMember familyMember) {
		try {
			ResponseEntity<List<FamilyMember>> response = familyMemberService.createMembersForFamily(familyId,
					familyMember);
			if (response.getStatusCode() == HttpStatus.CREATED) {
				return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Family not found with familyId: " + familyId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error: Failed to create FamilyMember.");
		}
	}

	@GetMapping("/getAllMembers")
	public ResponseEntity<Object> getAllFamilyMembers() {
		try {
			List<FamilyMember> familyMembers = familyMemberService.getAllFamilyMembers();
			return ResponseEntity.ok(familyMembers);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error: Failed to retrieve family members.");
		}
	}

	@GetMapping("/getMemberById/{familyMemberId}")
	public ResponseEntity<Object> getFamilyMemberById(@PathVariable Long familyMemberId) {
		try {
			ResponseEntity<FamilyMember> response = familyMemberService.getFamilyMemberById(familyMemberId);
			if (response.getStatusCode() == HttpStatus.OK) {
				return ResponseEntity.ok(response.getBody());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Family member not found with familyMemberId: " + familyMemberId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error: Failed to retrieve family member.");
		}
	}

	@PutMapping("/updateMemberById/{familyMemberId}")
	public ResponseEntity<Object> updateFamilyMember(@PathVariable Long familyMemberId,
			@RequestBody FamilyMember updatedFamilyMember) {
		try {
			ResponseEntity<FamilyMember> response = familyMemberService.updateFamilyMember(familyMemberId,
					updatedFamilyMember);
			if (response.getStatusCode() == HttpStatus.OK) {
				return ResponseEntity.ok(response.getBody());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Family member not found with familyMemberId: " + familyMemberId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error: Failed to update family member.");
		}
	}

	@DeleteMapping("/deleteMemberById/{familyMemberId}")
	public ResponseEntity<Object> deleteFamilyMember(@PathVariable Long familyMemberId) {
		try {
			ResponseEntity<String> response = familyMemberService.deleteFamilyMember(familyMemberId);
			if (response.getStatusCode() == HttpStatus.OK) {
				return ResponseEntity.ok(response.getBody());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Family member not found with familyMemberId: " + familyMemberId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error: Failed to delete family member.");
		}
	}
}