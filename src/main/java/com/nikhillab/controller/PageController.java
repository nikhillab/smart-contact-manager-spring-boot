package com.nikhillab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "SCM - Home");
		return "home";
	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "SCM - About");
		return "about";
	}

	@GetMapping("/services")
	public String services(Model model) {
		model.addAttribute("title", "SCM - Services");
		return "services";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("title", "SCM - Contact");
		return "contact";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "SCM - Login");
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("title", "SCM - Register");
		return "register";
	}
}
