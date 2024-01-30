package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.entity.Admin;
import com.project.service.AdminService;

@RestController
@RequestMapping("/superadmin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create/admin")
    public ResponseEntity<Object> createAdmin(@RequestBody Admin admin) {
        try {
            if (admin.getRoleType() != null) {
                Admin createdAdmin = adminService.createAdmin(admin);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
            } else {
                return new ResponseEntity<>(
                        "Error: Role type is required for creating an admin. Please provide a valid role type.",
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Failed to create admin - " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/admin/{adminId}")
    public ResponseEntity<Object> getAdminById(@PathVariable Long adminId) {
        try {
            Admin admin = adminService.getAdminById(adminId);
            if (admin != null) {
                return ResponseEntity.ok(admin);
            } else {
                return new ResponseEntity<>("Error: Admin with ID '" + adminId + "' not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Failed to retrieve admin - " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<Object> getAllAdmins() {
        try {
            Iterable<Admin> admins = adminService.getAllAdmins();
            return ResponseEntity.ok(admins);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Failed to retrieve admins - " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateAdmin/{adminId}")
    public ResponseEntity<Object> updateAdmin(@PathVariable Long adminId, @RequestBody Admin updatedAdmin) {
        try {
            Admin updated = adminService.updateAdmin(adminId, updatedAdmin);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            } else {
                return new ResponseEntity<>("Error: Admin with ID '" + adminId + "' not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Failed to update admin - " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAdmin/{adminId}")
    public ResponseEntity<Object> deleteAdminById(@PathVariable Long adminId) {
        try {
            adminService.deleteAdminById(adminId);
            return new ResponseEntity<>("Message: Admin with ID '" + adminId + "' deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Failed to delete admin - " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
