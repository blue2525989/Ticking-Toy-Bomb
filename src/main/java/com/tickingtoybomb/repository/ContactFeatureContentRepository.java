package com.tickingtoybomb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.ContactFeatureContent;

public interface ContactFeatureContentRepository extends JpaRepository<ContactFeatureContent, Long> {
	
	List<ContactFeatureContent> findById(Long count);

}
