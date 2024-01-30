package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.GroupEvent;

public interface GroupEventRepository extends JpaRepository<GroupEvent, Long> {

}
