package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.entity.Role;
import com.project.entity.User;
import com.project.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	Role role = new Role();
	
	public User createUser(User user) {
	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(1);
		return userRepository.save(user);
	}

	public User updateUser(Long userId, User updatedUser) {
		User existingUser = userRepository.findById(userId).orElse(null);
		
		if (existingUser != null) {
			if (updatedUser.getRoleType() != null) {
				existingUser.setRoleType(updatedUser.getRoleType());
			}
			if (updatedUser.getFirstName() != null) {
				existingUser.setFirstName(updatedUser.getFirstName());
			}
			if (updatedUser.getLastName() != null) {
				existingUser.setLastName(updatedUser.getLastName());
			}
			if (updatedUser.getContact() != null) {
				existingUser.setContact(updatedUser.getContact());
			}
			if (updatedUser.getEmail() != null) {
				existingUser.setEmail(updatedUser.getEmail());
			}
			if (updatedUser.getAddress() != null) {
				existingUser.setAddress(updatedUser.getAddress());
			}
			if (updatedUser.getPassword() != null) {
				existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
			}

			return userRepository.save(existingUser);
		}
		return null;
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

}