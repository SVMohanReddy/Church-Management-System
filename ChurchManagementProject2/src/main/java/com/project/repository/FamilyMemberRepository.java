package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.FamilyMember;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long>{


}
