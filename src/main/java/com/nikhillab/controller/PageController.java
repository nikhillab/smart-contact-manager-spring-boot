package com.nikhillab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Smart Contrat Manager - Home");
		return "home";
	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "Smart Contrat Manager - About");
		return "about";
	}

	@GetMapping("/services")
	public String services(Model model) {
		model.addAttribute("title", "Smart Contrat Manager - Services");
		return "services";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("title", "Smart Contrat Manager - Contact");
		return "contact";
	}
}
