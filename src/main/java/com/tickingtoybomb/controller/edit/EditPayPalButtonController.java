package com.tickingtoybomb.controller.edit;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.tickingtoybomb.controller.PermissionController;
import com.tickingtoybomb.model.PayPalButton;
import com.tickingtoybomb.repository.PayPalButtonRepository;

@Controller
public class EditPayPalButtonController extends PermissionController {

	
	private PayPalButtonRepository buttons;
	private AmazonS3Client s3client;
	
	@Autowired
	public EditPayPalButtonController(PayPalButtonRepository buttons, AmazonS3Client s3client) {
		this.buttons = buttons;
		this.s3client = s3client;
	}
	
	/*----------------------upload pay pal buttons--------------------------*/
	
	@RequestMapping("/edit-paypal-button")
	public String editPayPalButtonView(HttpSession session) {
		
		// admin user
		if (hasAdminRole()) {
			session.setAttribute("adminrole", hasAdminRole());
		}
		// regular user
		else if (hasUserRole()) {
			session.setAttribute("userrole", hasUserRole());
		}
		return "admin/edit-paypal-button";
	}
	
	@RequestMapping("/create-paypal-button")
	public String uploadPayPalButton(HttpSession session, Model model,
			@RequestParam String name, @RequestParam String content) {

		PayPalButton button = new PayPalButton();
		button.setName(name);
		button.setUrl(content);		
		buttons.save(button);
		
		// admin user
		if (hasAdminRole()) {
			session.setAttribute("adminrole", hasAdminRole());
		}
		// regular user
		else if (hasUserRole()) {
			session.setAttribute("userrole", hasUserRole());
		}

		String saved = "Button, " + name + ", has been saved.";
		model.addAttribute("saved", saved);
		return "admin/saved";
	}
	
	// delete element
	@GetMapping(path="/delete-paypal-button")
	public String deleteButtons(Long ID, Model model) {
		// need to add aws code to delete from bucket
		String file = buttons.findOne(ID).getName();
		buttons.delete(ID);
		s3client.deleteObject(new DeleteObjectRequest("ticking-toy-bomb-images/paypal-buttons", file));
		String saved = "The button with Name " + file + " has been deleted.";
		model.addAttribute("saved", saved);
		return "redirect:/saved";
	}
		
	// list all element
	@RequestMapping("/list-paypal-buttons")
	public String listButtons(Model model) {
		List<PayPalButton> realList = buttons.findAll();
		if (realList != null) {
			model.addAttribute("listMain", realList);
		}	
		return "admin/list-all-button";
	}
}
