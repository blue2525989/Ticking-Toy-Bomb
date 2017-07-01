package com.tickingtoybomb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tickingtoybomb.model.InventoryItem;
import com.tickingtoybomb.model.JumbotronContent;
import com.tickingtoybomb.repository.InventoryRepository;

@Controller
public class SearchController extends PermissionController {


	// instance of Repositories
	protected InventoryRepository inventory;
	
	// autowire the repository to the controller
	@Autowired
	public SearchController(InventoryRepository inventory) {
		this.inventory = inventory;
	}


	@RequestMapping("/search")
	public String searchItem(@RequestParam String item, HttpSession session, Model model) {
		List<InventoryItem> items = findByTypeItem(item);	
		items.addAll(findByHeadlineItem(items, item));
		if (items != null) {
			model.addAttribute("items", items);
		}
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
	
	// use this method second
	public List<InventoryItem> findByHeadlineItem(List<InventoryItem> itemsMain, String type) {
		List<InventoryItem> items = inventory.findAll();
		List<InventoryItem> realItems = new ArrayList<InventoryItem>();
		// need to break type and headline down if contains " "
		// use string array inside loop
		
		// go through list and add if type matches
		if (items != null) {
			for (int i = 0; i <= items.size()-1; i++) {				
				if (items.get(i).getHeadline().toLowerCase().contains(type.toLowerCase()) && 
						!itemsMain.contains(items.get(i))) {
					realItems.add(items.get(i));					
				}
				else if (items.get(i).getHeadline().equalsIgnoreCase(type) && 
						!itemsMain.contains(items.get(i))) {
					realItems.add(items.get(i));
				}
			}
		}		
		return realItems;
	}
	
	// use this method first
	public List<InventoryItem> findByTypeItem(String type) {
		List<InventoryItem> items = inventory.findAll();
		List<InventoryItem> realItems = new ArrayList<InventoryItem>();
		// go through list and add if type matches
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
