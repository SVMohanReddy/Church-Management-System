package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.Role;
import com.project.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public Role createOrUpdateMember(Role role)
	{
		return roleRepository.save(role);
	}
	
	@Transactional
	public Role getMemberById(int roleId)
	{
		return roleRepository.findById(roleId);
	}
	
	@Transactional
    public void deleteMemberById(int roleId) {
		roleRepository.deleteById(roleId);
    }
}
