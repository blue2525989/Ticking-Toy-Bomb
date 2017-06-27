package com.tickingtoybomb.controller.edit;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tickingtoybomb.controller.PermissionController;
import com.tickingtoybomb.model.Image;
import com.tickingtoybomb.model.InventoryItem;
import com.tickingtoybomb.repository.ImageRepository;
import com.tickingtoybomb.repository.InventoryRepository;

@Controller
public class EditInventoryController extends PermissionController {


	// instance of Repositories
	private InventoryRepository inventory;
	private ImageRepository imgRepo;
	
	// autowire the repository to the controller
	@Autowired
	public EditInventoryController(InventoryRepository inventory, ImageRepository imgRepo) {
		this.inventory = inventory;
		this.imgRepo = imgRepo;
	}
	
	@RequestMapping("/edit-inventory")
	public String adminInventory(HttpSession session) {
		
		// adds full list from gallery
		// need to work on slimming down list.
		List<Image> imageList = imgRepo.findAll();
		if (imageList != null) {
			session.setAttribute("imageList", imageList);
		}
		// admin user
		if (hasAdminRole()) {
			session.setAttribute("adminrole", hasAdminRole());
		}
		// regular user
		else if (hasUserRole()) {
			session.setAttribute("userrole", hasUserRole());
		}		
		return "admin/edit-inventory";
	}
	
	/* for editing services content */
	
	@PostMapping(path="/edit-inventory/edit-item")
	// request params to save
	public String addNewItem(Model model, @RequestParam String headline
			, @RequestParam String content, @RequestParam String type, 
			@RequestParam Double price,
			@RequestParam String url) {

		InventoryItem item = new InventoryItem();;
		item.setHeadline(headline); 
		item.setUrl(url);
		item.setContent(content);
		item.setType(type);
		item.setPrice(price);
		inventory.save(item);
		return "redirect:/edit-inventory";
	}
	
	// delete element
	@GetMapping(path="/delete-item")
	public String deleteItem(Long ID, HttpSession session) {
		inventory.delete(ID);
		String name = inventory.getOne(ID).getHeadline();
		// add javaScript document pop notifcation
		String saved = "The Item " + name + " has been deleted.";
		session.setAttribute("saved", saved);
		return "redirect:/saved";
	}
		
	// list all element
	@RequestMapping("/list-items")
	public String listAllitems(Model model, HttpSession session) {
		List<InventoryItem> items = inventory.findAll();
		// Iterator iter = jumboList.iterator();
		if (items != null) {
			model.addAttribute("items", items); /* all named list for uniformity */
		}	
		// admin user
		if (hasAdminRole()) {
			session.setAttribute("adminrole", hasAdminRole());
		}
		// regular user
		else if (hasUserRole()) {
			session.setAttribute("userrole", hasUserRole());
		}
		return "admin/list-all-type";
	}
	
}
