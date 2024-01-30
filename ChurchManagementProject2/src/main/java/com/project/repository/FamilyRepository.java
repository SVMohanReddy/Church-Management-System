package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Family;

public interface FamilyRepository extends JpaRepository<Family, Long> {
}