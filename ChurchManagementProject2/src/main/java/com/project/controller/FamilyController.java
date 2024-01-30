package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Family;
import com.project.service.FamilyService;

@RestController
@RequestMapping("/pastor")
public class FamilyController {

	@Autowired
	private FamilyService familyService;
	
	@PostMapping("/create")
    public ResponseEntity<Object> createFamily(@RequestBody Family family) {
        try {
            Family createdFamily = familyService.createFamily(family);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFamily);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Failed to create family - " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/family/{familyId}")
	public ResponseEntity<Object> getFamilyById(@PathVariable Long familyId) {
		try {
			Family family = familyService.getFamilyById(familyId);
			if (family != null) {
				return ResponseEntity.ok(family);
			} else {
				return new ResponseEntity<>("Error: Family with ID '" + familyId + "' not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to retrieve family - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getAllFamilies")
	public ResponseEntity<Object> getAllFamilies() {
		try {
			List<Family> families = familyService.getAllFamilies();
			return ResponseEntity.ok(families);
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to retrieve families - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateFamily/{familyId}")
	public ResponseEntity<Object> updateFamily(@PathVariable Long familyId, @RequestBody Family updatedFamily) {
		try {
			Family updated = familyService.updateFamily(familyId, updatedFamily);
			if (updated != null) {
				return ResponseEntity.ok(updated);
			} else {
				return new ResponseEntity<>("Error: Family with ID '" + familyId + "' not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to update Family - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteFamily/{familyId}")
	public ResponseEntity<Object> deleteFamilyById(@PathVariable Long familyId) {
		try {
			familyService.deleteFamilyById(familyId);
			return new ResponseEntity<>("Message: Family with ID '" + familyId + "' deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to delete Family - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
