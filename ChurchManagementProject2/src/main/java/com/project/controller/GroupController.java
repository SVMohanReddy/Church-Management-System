package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.entity.Groupp;
import com.project.service.GroupService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ministry")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@GetMapping("/findAllgroups")
	public ResponseEntity<List<Groupp>> getAllGroups() {
		List<Groupp> groups = groupService.getAllGroups();
		return new ResponseEntity<>(groups, HttpStatus.OK);
	}

	@GetMapping("/findByGroupId/{groupId}")
	public ResponseEntity<Groupp> getGroupById(@PathVariable Long groupId) {
		Optional<Groupp> group = groupService.getGroupById(groupId);
		return group.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/create/group")
	public ResponseEntity<Groupp> createGroup(@RequestBody Groupp group) {
		Groupp createdGroup = groupService.createGroup(group);
		return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteBygroupId/{groupId}")
	public ResponseEntity<Void> deleteById(@PathVariable Long groupId) {
		groupService.deleteGroup(groupId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
