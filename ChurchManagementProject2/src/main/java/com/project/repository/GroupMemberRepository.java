package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.GroupMember;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long>{


}
