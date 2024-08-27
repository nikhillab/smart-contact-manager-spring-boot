package com.nikhillab.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nikhillab.entities.Contact;
import com.nikhillab.entities.User;

public interface ContactRepo extends JpaRepository<Contact, Long> {
    
    Page<Contact> findByUser(User user, Pageable pageable);
    Page<Contact> findByUserAndNameContaining(User user, String namekeyword, Pageable pageable);
    Page<Contact> findByUserAndEmailContaining(User user, String emailkeyword, Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phonekeyword, Pageable pageable);


    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);

}
