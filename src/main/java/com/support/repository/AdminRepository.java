package com.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.support.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{

}
