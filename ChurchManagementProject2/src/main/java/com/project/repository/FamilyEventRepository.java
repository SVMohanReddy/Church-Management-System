package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.FamilyEvent;

public interface FamilyEventRepository extends JpaRepository<FamilyEvent, Long> {

}
