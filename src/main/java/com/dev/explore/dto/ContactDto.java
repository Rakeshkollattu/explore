package com.dev.explore.dto;

import com.dev.explore.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto {

    private String name;
    private String email;
    private String phoneNumber;
    private String bloodGroup;
    private String title;
    private String address;

    public ContactDto(String name, String email, String phoneNumber, String bloodGroup, String title, String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bloodGroup = bloodGroup;
        this.title = title;
        this.address = address;
    }

    public Contact getContact() {
        Contact contact = new Contact();
        contact.setName(this.getName());
        contact.setEmail(this.getEmail());
        contact.setPhoneNumber(this.getPhoneNumber());
        contact.setBloodGroup(this.getBloodGroup());
        contact.setTitle(this.getTitle());
        contact.setAddress(this.getAddress());
        return contact;
    }

}
