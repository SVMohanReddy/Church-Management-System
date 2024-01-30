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

import com.project.entity.User;
import com.project.service.UserService;

@RestController
@RequestMapping("/superadmin")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create/user")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		try {
			if (user.getRoleType() != null) {
				User createdUser = userService.createUser(user);
				return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
			} else {
				return new ResponseEntity<>(
						"Error: Invalid or missing roleType. Only 'user' roleType is allowed for creating.",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to create user - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get/user/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
		try {
			User user = userService.getUserById(userId);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return new ResponseEntity<>("Error: User with ID '" + userId + "' not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to retrieve user - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<Object> getAllUsers() {
		try {
			List<User> users = userService.getAllUsers();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to retrieve users - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateUser/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        try {
            User updated = userService.updateUser(userId, updatedUser);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            } else {
                return new ResponseEntity<>("Error: User with ID '" + userId + "' not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Failed to update user - " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<Object> deletePastorById(@PathVariable Long userId) {
		try {
			userService.deleteUserById(userId);
			return new ResponseEntity<>("Message: User with ID '" + userId + "' deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error: Failed to delete User - " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
