package com.tickingtoybomb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.InventoryItem;


public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
	
	List<InventoryItem> findById(Long count);

	List<InventoryItem> findByType(String type);
}
