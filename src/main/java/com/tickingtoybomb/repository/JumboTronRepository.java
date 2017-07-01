package com.tickingtoybomb.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.JumbotronContent;


public interface JumboTronRepository extends JpaRepository<JumbotronContent, Long> {
	
	JumbotronContent findById(Long id);
	
}
