package com.tickingtoybomb.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tickingtoybomb.model.InventoryItem;
import com.tickingtoybomb.repository.InventoryRepository;


@Controller
public class InventoryController extends PermissionController {
	

	// instance of Repositories
	private InventoryRepository inventory;
	
	// autowire the repository to the controller
	@Autowired
	public InventoryController(InventoryRepository inventory) {
		this.inventory = inventory;
	}

	@RequestMapping("/inventory")
	public String services(HttpSession session) {
		// adds last jumbo 
		List<InventoryItem> items = inventory.findAll();		
		if (items != null) {
			session.setAttribute("items", items);
		}
		// admin user
		if (hasAdminRole()) {
			session.setAttribute("adminrole", hasAdminRole());
			}
		// regular user
		else if (hasUserRole()) {
			session.setAttribute("userrole", hasUserRole());
		}
		return "inventory/inventory";
	}
}
