package com.smart.contact.service;

import com.smart.contact.dto.ContactDTO;
import com.smart.contact.dto.Mapper;
import com.smart.contact.entity.Contact;
import com.smart.contact.entity.User;
import com.smart.contact.exception.ResourceNotFound;
import com.smart.contact.repository.ContactRepository;
import com.smart.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    public Contact getContactById(int id){
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Contact Not Found: " + id));
    }

    public Contact addContact(Contact contact, int userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User Not Found: " + userId));

        contact.setUser(user);
        return contactRepository.save(contact);
    }

    public void deleteContact(int id){
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Contact Not Found: " + id));

        contactRepository.delete(contact);
    }

    public Contact updateContact(int id, Contact newContact){
        Contact existing = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Contact Not Found: " + id));

        existing.setFirstName(newContact.getFirstName());
        existing.setLastName(newContact.getLastName());
        existing.setEmail(newContact.getEmail());
        existing.setWork(newContact.getWork());
        existing.setPhone(newContact.getPhone());
        existing.setDescription(newContact.getDescription());

        return contactRepository.save(existing);
    }

    public Page<ContactDTO> getContacts(int page, int size, String sortBy, boolean asc){
        Sort sort = asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return contactRepository.findAll(pageable)
                .map(Mapper::toContactDTO);
    }
}