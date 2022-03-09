package com.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.support.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
