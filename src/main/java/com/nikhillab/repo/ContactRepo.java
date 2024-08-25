package com.nikhillab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikhillab.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Long> {

}
