package com.tickingtoybomb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tickingtoybomb.model.GalleryCardContent;
import com.tickingtoybomb.model.JumbotronContent;
import com.tickingtoybomb.repository.GalleryCardContentRepository;

@Controller
public class GalleryController extends PermissionController {

	private GalleryCardContentRepository card;
	
	public GalleryController(GalleryCardContentRepository card) {
		this.card = card;
	}
	
	@RequestMapping("/gallery")
	public String gallery(HttpSession session, Model model) {
		// adds the three most current cards
		findLastNineCards(model);
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
		return "gallery/gallery";
	}
	
	private void findLastNineCards(Model model) {
		long size = card.count();
		List<GalleryCardContent> realList = new ArrayList<GalleryCardContent>();
		GalleryCardContent card1 = new GalleryCardContent();
		GalleryCardContent card2 = new GalleryCardContent();
		GalleryCardContent card3 = new GalleryCardContent();
		GalleryCardContent card4 = new GalleryCardContent();
		GalleryCardContent card5 = new GalleryCardContent();
		GalleryCardContent card6 = new GalleryCardContent();
		if (size > 0) {
			List<GalleryCardContent> cards = card.findAll();
			// reverse list to get newest ones first
			for (int i = 0; i <= cards.size()-1; i++) {
				if (cards.get(i).getType().equals("card1")) {
					card1 = cards.get(i);
					realList.add(card1);
				} else if (cards.get(i).getType().equals("card2")) {
					card2 = cards.get(i);
					realList.add(card2);
				} else if (cards.get(i).getType().equals("card3")) {
					card3 = cards.get(i);
					realList.add(card3);
				} else if (cards.get(i).getType().equals("card4")) {
					card4 = cards.get(i);
					realList.add(card4);
				} else if (cards.get(i).getType().equals("card5")) {
					card5 = cards.get(i);
					realList.add(card5);
				} else if (cards.get(i).getType().equals("card6")) {
					card6 = cards.get(i);
					realList.add(card6);
				}
			}
			if (realList != null) {
				model.addAttribute("cardList", realList);
			}
		} else {
			card1.setHeadline("Blue's website and software design");
			String content = "Welcome to Blue's website and software design. This site is brand new "
					+ "and is the model for the site I hope to market. Everything is self contained "
					+ "and can be edited from the administration account. If your interesterd, send "
					+ "me an email on the contact page.";
			card1.setContent(content);
			card1.setUrl("https://s3-us-west-2.amazonaws.com/blue-company-images/computer-02.jpg");
			card2.setHeadline("Blue's website and software design");
			card2.setContent(content);
			card2.setUrl("https://s3-us-west-2.amazonaws.com/blue-company-images/computer-02.jpg");
			card3.setHeadline("Blue's website and software design");
			card3.setContent(content);
			card3.setUrl("https://s3-us-west-2.amazonaws.com/blue-company-images/computer-02.jpg");
			card4.setHeadline("Blue's website and software design");
			card4.setContent(content);
			card4.setUrl("https://s3-us-west-2.amazonaws.com/blue-company-images/computer-02.jpg");
			card5.setHeadline("Blue's website and software design");
			card5.setContent(content);
			card5.setUrl("https://s3-us-west-2.amazonaws.com/blue-company-images/computer-02.jpg");
			card6.setHeadline("Blue's website and software design");
			card6.setContent(content);
			card6.setUrl("https://s3-us-west-2.amazonaws.com/blue-company-images/computer-02.jpg");
			realList.add(card1);
			realList.add(card2);
			realList.add(card3);
			realList.add(card4);
			realList.add(card5);
			realList.add(card6);
			model.addAttribute("cardList", realList);
		}
	}
}
