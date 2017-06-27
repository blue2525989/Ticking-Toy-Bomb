package com.tickingtoybomb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.JumbotronContent;


public interface JumboTronRepository extends JpaRepository<JumbotronContent, Long> {
	
	List<JumbotronContent> findById(Long id);
	
}
