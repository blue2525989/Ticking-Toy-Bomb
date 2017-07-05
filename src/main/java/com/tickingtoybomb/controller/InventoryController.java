package com.tickingtoybomb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tickingtoybomb.model.InventoryItem;
import com.tickingtoybomb.model.JumbotronContent;
import com.tickingtoybomb.repository.InventoryRepository;


@Controller
public class InventoryController extends PermissionController {
	

	// instance of Repositories
	protected InventoryRepository inventory;
	
	// autowire the repository to the controller
	@Autowired
	public InventoryController(InventoryRepository inventory) {
		this.inventory = inventory;
	}

	@RequestMapping("/inventory")
	public String services(HttpSession session, Model model) {
		addTypesForMenu(model);
		// adds last jumbo 
		JumbotronContent jumboMain = findLastJumbo();		
		if (jumboMain != null) {
			session.setAttribute("jumboMain", jumboMain);
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
	

	@RequestMapping("/inventory-{type}")
	public String necklace(HttpSession session, Model model, @PathVariable String type) {
		addTypesForMenu(model);
		// adds last jumbo 
		JumbotronContent jumboMain = findLastJumbo();		
		if (jumboMain != null) {
			session.setAttribute("jumboMain", jumboMain);
		}
		List<InventoryItem> items = findByTypeItem(type);		
		if (items != null) {
			model.addAttribute("items", items);
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
	
	public List<InventoryItem> findByTypeItem(String type) {
		List<InventoryItem> items = inventory.findAll();
		List<InventoryItem> realItems = new ArrayList<InventoryItem>();
		// goes backwards to get newest.
		if (items != null) {
			for (int i = 0; i <= items.size()-1; i++) {
				if (items.get(i).getType().equalsIgnoreCase(type)) {
					realItems.add(items.get(i));
				}
			}
		}		
		return realItems;
	}
}
