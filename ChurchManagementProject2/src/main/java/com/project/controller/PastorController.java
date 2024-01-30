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

import com.project.entity.Pastor;
import com.project.service.PastorService;

@RestController
@RequestMapping("/superadmin")
public class PastorController {

	@Autowired
	private PastorService pastorService;

	@PostMapping("/create/pastor")
	public ResponseEntity<Object> createPastor(@RequestBody Pastor pastor) {
		try {
			if (pastor.getRoleType() != null && "pastor".equalsIgnoreCase(pastor.getRoleType())) {
				Pastor createdPastor = pastorService.createPastor(pastor);
				return ResponseEntity.status(HttpStatus.CREATED).body(createdPastor);
			} else {
				return new ResponseEntity<>(
						"Error: Invalid or missing roleType. Only 'pastor' roleType is allowed for creating pastors.",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to create pastor - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updatePastor/{pastorId}")
    public ResponseEntity<Object> updatePastor(@PathVariable Long pastorId, @RequestBody Pastor updatePastor) {
        try {
        	Pastor updated = pastorService.updatePastor(pastorId, updatePastor);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            } else {
                return new ResponseEntity<>("Error: Pastor with ID '" + pastorId + "' not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Failed to update Pastor - " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/pastor/{id}")
	public ResponseEntity<Object> getPastorById(@PathVariable Long id) {
		try {
			Pastor pastor = pastorService.getPastorById(id);
			if (pastor != null) {
				return ResponseEntity.ok(pastor);
			} else {
				return new ResponseEntity<>("Error: Pastor with ID '" + id + "' not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to retrieve pastor - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllPastors")
	public ResponseEntity<Object> getAllPastors() {
		try {
			List<Pastor> pastors = pastorService.getAllPastors();
			return ResponseEntity.ok(pastors);
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to retrieve pastors - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deletePastor/{id}")
	public ResponseEntity<Object> deletePastorById(@PathVariable Long id) {
		try {
			pastorService.deletePastorById(id);
			return new ResponseEntity<>("Message: Pastor with ID '" + id + "' deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to delete pastor - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
