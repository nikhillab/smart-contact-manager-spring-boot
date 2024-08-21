package com.nikhillab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nikhillab.dto.Message;
import com.nikhillab.dto.UserForm;
import com.nikhillab.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

	private final UserService userService;

	public PageController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String main(Model model) {
		return "redirect:/home";
	}

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
		UserForm userForm = new UserForm();
		// for default values in form

		model.addAttribute("userForm", userForm);
		return "register";
	}

	@PostMapping("/register")
	public String registerNewUser(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (bindingResult.hasErrors()) {
			System.out.println("Inside error block");
			return "register";
		}

		System.out.println("Inside registerNewUser");

		userService.saveUser(userForm);

		Message message = new Message("User registered", Message.MessageType.blue);

		session.setAttribute("message", message);

		return "redirect:/register";
	}
}
