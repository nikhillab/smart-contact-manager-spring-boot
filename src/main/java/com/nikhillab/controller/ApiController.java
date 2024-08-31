package com.nikhillab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhillab.entities.Contact;
import com.nikhillab.service.ContactService;



@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contact/{contactId}")
    public Contact getContact(@PathVariable Long contactId) {
        return contactService.getById(contactId);
    }

}