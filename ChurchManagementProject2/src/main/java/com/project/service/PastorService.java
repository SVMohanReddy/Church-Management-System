package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.entity.Pastor;
import com.project.repository.PastorRepository;

@Service
public class PastorService {

	@Autowired
	private PastorRepository pastorRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Pastor createPastor(Pastor pastor) {

		pastor.setPassword(passwordEncoder.encode(pastor.getPassword()));
		pastor.setActive(1);

		return pastorRepository.save(pastor);
	}

	public Pastor updatePastor(Long pastorId, Pastor updatedPastor) {
		Pastor existingPastor = pastorRepository.findById(pastorId).orElse(null);
		
		if (existingPastor != null) {
			if (updatedPastor.getRoleType() != null) {
				existingPastor.setRoleType(updatedPastor.getRoleType());
			}
			if (updatedPastor.getFirstName() != null) {
				existingPastor.setFirstName(updatedPastor.getFirstName());
			}
			if (updatedPastor.getLastName() != null) {
				existingPastor.setLastName(updatedPastor.getLastName());
			}
			if (updatedPastor.getContact() != null) {
				existingPastor.setContact(updatedPastor.getContact());
			}
			if (updatedPastor.getEmail() != null) {
				existingPastor.setEmail(updatedPastor.getEmail());
			}
			if (updatedPastor.getAddress() != null) {
				existingPastor.setAddress(updatedPastor.getAddress());
			}
			if (updatedPastor.getPassword() != null) {
				existingPastor.setPassword(passwordEncoder.encode(updatedPastor.getPassword()));
			}

			return pastorRepository.save(existingPastor);
		}
		return null;
	}
	
	public Pastor getPastorById(Long id) {
		return pastorRepository.findById(id).orElse(null);
	}

	public List<Pastor> getAllPastors() {
		return pastorRepository.findAll();
	}

	public void deletePastorById(Long id) {
		pastorRepository.deleteById(id);
	}

}
