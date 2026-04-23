package com.smart.contact.dto;

import com.smart.contact.entity.Contact;
import com.smart.contact.entity.User;

public class Mapper {

    public static UserDTO toUserDTO(User user){
        UserDTO dto=new UserDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAbout(user.getAbout());
        dto.setRole(user.getRole());
        return dto;
    }

    public static ContactDTO toContactDTO(Contact contact){
        ContactDTO dto=new ContactDTO();

        dto.setcId(contact.getcId());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setEmail(contact.getEmail());
        dto.setWork(contact.getWork());
        dto.setDescription(contact.getDescription());
        return dto;
    }
}
