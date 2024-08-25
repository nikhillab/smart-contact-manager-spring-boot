package com.nikhillab.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nikhillab.dto.ContactForm;
import com.nikhillab.dto.Message;
import com.nikhillab.dto.Message.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @GetMapping("/add")
    public String addContact(Model model) {
        ContactForm contactForm = new ContactForm();
        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        model.addAttribute("title", "SCM - Add Contact");

        return "user/add_contact";
    }

    @PostMapping("/add")
    public String postMethodName(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
            Authentication authentication, HttpSession session) {
        if (result.hasErrors()) {

            // result.getAllErrors().forEach(error -> logger.info(error.toString()));
            session.setAttribute("message",
                    new Message("Please correct the following errors", Message.MessageType.red));
            return "user/add_contact";
        }

        return "redirect:/user/contacts/add";
    }

}
