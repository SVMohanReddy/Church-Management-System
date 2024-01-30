package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Family;
import com.project.repository.FamilyRepository;

@Service
public class FamilyService {

	@Autowired
	private FamilyRepository familyRepository;

	public Family createFamily(Family family) {
		return familyRepository.save(family);
	}

	public Family updateFamily(Long familyId, Family updatedFamily) {
		Family existingFamily = familyRepository.findById(familyId).orElse(null);

		if (existingFamily != null) {
			if (updatedFamily.getFamilyName() != null) {
				existingFamily.setFamilyName(updatedFamily.getFamilyName());
			}
			if (updatedFamily.getFamilyHeadName() != null) {
				existingFamily.setFamilyHeadName(updatedFamily.getFamilyHeadName());
			}
			if (updatedFamily.getContact() != null) {
				existingFamily.setContact(updatedFamily.getContact());
			}
			if (updatedFamily.getEmail() != null) {
				existingFamily.setEmail(updatedFamily.getEmail());
			}
			if (updatedFamily.getAddress() != null) {
				existingFamily.setAddress(updatedFamily.getAddress());
			}

			return familyRepository.save(existingFamily);
		}
		return null;
	}

	public Family getFamilyById(Long familyId) {
		return familyRepository.findById(familyId).orElse(null);
	}

	public List<Family> getAllFamilies() {
		return familyRepository.findAll();
	}

	public void deleteFamilyById(Long familyId) {
		familyRepository.deleteById(familyId);
	}
}
