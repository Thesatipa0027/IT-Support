package com.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.support.entity.Priority;

public interface PrioriryRepository extends JpaRepository<Priority, Long> {

}
