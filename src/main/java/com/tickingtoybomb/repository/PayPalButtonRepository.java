package com.tickingtoybomb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickingtoybomb.model.PayPalButton;


public interface PayPalButtonRepository extends JpaRepository<PayPalButton, Long> {
	
	List<PayPalButton> findById(Long count);
}
