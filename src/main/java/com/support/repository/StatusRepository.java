package com.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.support.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
