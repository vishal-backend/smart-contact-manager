package com.smart.contact.controller;

import com.smart.contact.dto.ContactDTO;
import com.smart.contact.entity.Contact;
import com.smart.contact.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable int id){
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ContactDTO>> getContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "cId") String sortBy,
            @RequestParam(defaultValue = "true") boolean asc){

        return ResponseEntity.ok(contactService.getContacts(page, size, sortBy, asc));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Contact> addContact(
            @Valid @RequestBody Contact contact,
            @PathVariable int userId){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contactService.addContact(contact, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable int id){
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(
            @Valid @RequestBody Contact contact,
            @PathVariable int id){

        return ResponseEntity.ok(contactService.updateContact(id, contact));
    }
}