package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Groupp;
import com.project.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

	@Autowired
    private GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Groupp> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Groupp> getGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public Groupp createGroup(Groupp group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long groupId)
    {
    	groupRepository.deleteById(groupId);
    }
}