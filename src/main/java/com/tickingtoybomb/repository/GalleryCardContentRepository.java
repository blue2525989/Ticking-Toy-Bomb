package com.tickingtoybomb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.GalleryCardContent;


public interface GalleryCardContentRepository extends JpaRepository<GalleryCardContent, Long> {
	
	List<GalleryCardContent> findById(Long count);

}
