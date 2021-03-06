package com.tickingtoybomb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.AboutContent;

public interface AboutContentRepository extends JpaRepository<AboutContent, Long> {
	
	List<AboutContent> findById(Long count);

}
