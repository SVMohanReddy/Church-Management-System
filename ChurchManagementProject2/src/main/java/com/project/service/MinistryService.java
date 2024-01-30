package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.entity.Ministry;
import com.project.repository.MinistryRepository;

@Service
public class MinistryService {

	@Autowired
	private MinistryRepository ministryRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Ministry createMinistry(Ministry ministry) {

		ministry.setPassword(passwordEncoder.encode(ministry.getPassword()));
		ministry.setActive(1);

		return ministryRepository.save(ministry);
	}
	
	public Ministry updateMinistry(Long ministryId, Ministry updatedMinistry) {
		Ministry existingMinistry = ministryRepository.findById(ministryId).orElse(null);
		
		if (existingMinistry != null) {
			if (updatedMinistry.getRoleType() != null) {
				existingMinistry.setRoleType(updatedMinistry.getRoleType());
			}
			if (updatedMinistry.getFirstName() != null) {
				existingMinistry.setFirstName(updatedMinistry.getFirstName());
			}
			if (updatedMinistry.getLastName() != null) {
				existingMinistry.setLastName(updatedMinistry.getLastName());
			}
			if (updatedMinistry.getContact() != null) {
				existingMinistry.setContact(updatedMinistry.getContact());
			}
			if (updatedMinistry.getEmail() != null) {
				existingMinistry.setEmail(updatedMinistry.getEmail());
			}
			if (updatedMinistry.getAddress() != null) {
				existingMinistry.setAddress(updatedMinistry.getAddress());
			}
			if (updatedMinistry.getPassword() != null) {
				existingMinistry.setPassword(passwordEncoder.encode(updatedMinistry.getPassword()));
			}

			return ministryRepository.save(existingMinistry);
		}
		return null;
	}


	public Ministry getMinistryById(Long id) {
		return ministryRepository.findById(id).orElse(null);
	}

	public List<Ministry> getAllMinistries() {
		return ministryRepository.findAll();
	}

	public void deleteMinistryById(Long id) {
		ministryRepository.deleteById(id);
	}

}
