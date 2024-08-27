package com.nikhillab.controller;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nikhillab.dto.AppConstants;
import com.nikhillab.dto.ContactForm;
import com.nikhillab.dto.ContactSearchForm;
import com.nikhillab.dto.Message;
import com.nikhillab.entities.Contact;
import com.nikhillab.entities.User;
import com.nikhillab.service.ContactService;
import com.nikhillab.service.ImageService;
import com.nikhillab.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

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

        // form ---> contact

        User user = userService.getUserByEmail(authentication.getName());
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            String filename = UUID.randomUUID().toString();
            String fileURL;
            try {
                fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);
                contact.setPicture(fileURL);
            } catch (IOException e) {
                session.setAttribute("message",
                        new Message("Error While saving Contact Image", Message.MessageType.red));
                return "user/add_contact";
            }

        }
        contactService.save(contact);
        System.out.println(contactForm);

        return "redirect:/user/contact/add";
    }

    @GetMapping()
    public String getContact(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
            Authentication authentication) {

        // load all the user contacts
        String username = authentication.getName();

        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, direction);
        model.addAttribute("title", "SCM - Contacts");
        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        model.addAttribute("contactSearchForm", new ContactSearchForm());

        return "user/contact";
    }

    @RequestMapping("/search")
    public String searchHandler(

            @ModelAttribute ContactSearchForm contactSearchForm,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication) {

        logger.info("field {} keyword {}", contactSearchForm.getField(), contactSearchForm.getValue());

        var user = userService.getUserByEmail(authentication.getName());

        Page<Contact> pageContact = null;
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            pageContact = contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("phone")) {
            pageContact = contactService.searchByPhoneNumber(contactSearchForm.getValue(), size, page, sortBy,
                    direction, user);
        }

        logger.info("pageContact {}", pageContact);
        
        model.addAttribute("title", "Search Contacts");

        model.addAttribute("contactSearchForm", contactSearchForm);

        model.addAttribute("pageContact", pageContact);

        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        

        return "user/search";
    }

}
