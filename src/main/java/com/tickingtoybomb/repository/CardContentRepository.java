package com.tickingtoybomb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.CardContent;


public interface CardContentRepository extends JpaRepository<CardContent, Long> {
	
	List<CardContent> findById(Long count);

	List<CardContent> findByType(String type);
}
