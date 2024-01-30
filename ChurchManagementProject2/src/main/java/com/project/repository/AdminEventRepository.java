package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.AdminEvent;

public interface AdminEventRepository extends JpaRepository<AdminEvent, Long> {

}
