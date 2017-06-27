package com.tickingtoybomb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.ServicesContent;


public interface ServicesRepository extends JpaRepository<ServicesContent, Long> {
	
	List<ServicesContent> findById(Long count);

	List<ServicesContent> findByType(String type);
}
