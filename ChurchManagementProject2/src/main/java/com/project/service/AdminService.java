package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.entity.Admin;
import com.project.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setActive(1);
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long adminId, Admin updatedAdmin) {
        Admin existingAdmin = adminRepository.findById(adminId).orElse(null);

        if (existingAdmin != null) {
            if (updatedAdmin.getRoleType() != null) {
                existingAdmin.setRoleType(updatedAdmin.getRoleType());
            }
            if (updatedAdmin.getFirstName() != null) {
                existingAdmin.setFirstName(updatedAdmin.getFirstName());
            }
            if (updatedAdmin.getLastName() != null) {
                existingAdmin.setLastName(updatedAdmin.getLastName());
            }
            if (updatedAdmin.getContact() != null) {
                existingAdmin.setContact(updatedAdmin.getContact());
            }
            if (updatedAdmin.getEmail() != null) {
                existingAdmin.setEmail(updatedAdmin.getEmail());
            }
            if (updatedAdmin.getAddress() != null) {
                existingAdmin.setAddress(updatedAdmin.getAddress());
            }
            if (updatedAdmin.getPassword() != null) {
                existingAdmin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
            }

            return adminRepository.save(existingAdmin);
        }
        return null;
    }

    public Admin getAdminById(Long adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    public Iterable<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public void deleteAdminById(Long adminId) {
        adminRepository.deleteById(adminId);
    }
}
