package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Groupp;

public interface GroupRepository extends JpaRepository<Groupp,Long>
{
	
}