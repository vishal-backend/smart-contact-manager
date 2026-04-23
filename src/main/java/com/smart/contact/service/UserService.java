package com.smart.contact.service;

import com.smart.contact.dto.Mapper;
import com.smart.contact.dto.UserDTO;
import com.smart.contact.entity.User;
import com.smart.contact.exception.ResourceNotFound;
import com.smart.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(int id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User Not Found: " + id));
    }

    public void deleteUser(int id){
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public User updateUser(int id, User newUser){
        User existing = getUserById(id);

        existing.setName(newUser.getName());
        existing.setEmail(newUser.getEmail());
        existing.setAbout(newUser.getAbout());
        existing.setRole(newUser.getRole());
        existing.setEnabled(newUser.isEnabled());

        if(newUser.getPassword() != null && !newUser.getPassword().isEmpty()){
            existing.setPassword(newUser.getPassword()); // ✅ FIX
        }

        return userRepository.save(existing);
    }

    public Page<UserDTO> getUser(int page, int size, String sortBy, boolean asc){
        Sort sort = asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return userRepository.findAll(pageable)
                .map(Mapper::toUserDTO);
    }
}