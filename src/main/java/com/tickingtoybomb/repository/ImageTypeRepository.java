package com.tickingtoybomb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.ImageTypes;


public interface ImageTypeRepository extends JpaRepository<ImageTypes, Long> {
	
	List<ImageTypes> findById(Long count);

	List<ImageTypes> findByType(String type);
}
