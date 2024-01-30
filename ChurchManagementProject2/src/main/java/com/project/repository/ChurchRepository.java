package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Church;

public interface ChurchRepository extends JpaRepository<Church, Integer> {

}
