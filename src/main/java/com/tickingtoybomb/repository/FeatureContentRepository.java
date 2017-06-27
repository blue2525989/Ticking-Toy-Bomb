package com.tickingtoybomb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.FeatureContent;

public interface FeatureContentRepository extends JpaRepository<FeatureContent, Long> {
	
	List<FeatureContent> findById(Long count);

}
