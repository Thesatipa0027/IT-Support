package com.support.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.support.entity.Admin;
import com.support.repository.AdminRepository;
import com.support.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	private final AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public List<Admin> adminList() {
		return adminRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		adminRepository.deleteById(id);;
	}

	@Override
	public Optional<Admin> getAdmin(Long id) {
		return adminRepository.findById(id);
	}
	
	
	
	
		
}
