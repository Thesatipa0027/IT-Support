package com.support.service;

import java.util.List;
import java.util.Optional;

import com.support.entity.Admin;

public interface AdminService {
		
	 Admin  saveAdmin(Admin admin);
	 
	 List<Admin> adminList();
	 
	 void delete(Long id);

		Optional<Admin> getAdmin(Long id);
}
