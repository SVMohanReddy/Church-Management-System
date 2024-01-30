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

import com.project.entity.Ministry;
import com.project.service.MinistryService;

@RestController
@RequestMapping("/superadmin")
public class MinistryController {

	@Autowired
	private MinistryService ministryService;
	
	@PostMapping("/create/ministry")
	public ResponseEntity<Object> createMinistry(@RequestBody Ministry ministry) {
		try {
			if (ministry.getRoleType() != null && "ministry".equalsIgnoreCase(ministry.getRoleType())) {
				Ministry createdMinistry = ministryService.createMinistry(ministry);
				return ResponseEntity.status(HttpStatus.CREATED).body(createdMinistry);
			} else {
				return new ResponseEntity<>(
						"Error: Invalid or missing roleType. Only 'ministry' roleType is allowed for creating ministries.",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to create ministry - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateMinistry/{ministryId}")
    public ResponseEntity<Object> updateMinistry(@PathVariable Long ministryId, @RequestBody Ministry updateMinistry) {
        try {
            Ministry updated = ministryService.updateMinistry(ministryId, updateMinistry);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            } else {
                return new ResponseEntity<>("Error: Ministry with ID '" + ministryId + "' not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Failed to update Ministry - " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/ministry/{id}")
	public ResponseEntity<Object> getMinistryById(@PathVariable Long id) {
		try {
			Ministry ministry = ministryService.getMinistryById(id);
			if (ministry != null) {
				return ResponseEntity.ok(ministry);
			} else {
				return new ResponseEntity<>("Error: Ministry with ID '" + id + "' not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to retrieve ministry - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllMinistries")
	public ResponseEntity<Object> getAllMinistries() {
		try {
			List<Ministry> ministries = ministryService.getAllMinistries();
			return ResponseEntity.ok(ministries);
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to retrieve ministries - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteMinistry/{id}")
	public ResponseEntity<Object> deleteMinistryById(@PathVariable Long id) {
		try {
			ministryService.deleteMinistryById(id);
			return new ResponseEntity<>("Message: Ministry with ID '" + id + "' deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to delete ministry - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
