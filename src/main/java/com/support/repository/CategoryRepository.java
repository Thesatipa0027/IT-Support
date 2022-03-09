package com.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.support.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
