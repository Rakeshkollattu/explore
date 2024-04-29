package com.dev.explore.service.impl;

import com.dev.explore.entity.Contact;
import com.dev.explore.repo.ContactRepository;
import com.dev.explore.response.ContactResponse;
import com.dev.explore.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepo;

    @Override
    public Page<Contact> getAllContacts(int pageNumber, int pageSize) {
        return contactRepo.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    @Override
    public Optional<Contact> getContact(Long id) {
        return contactRepo.findById(id);
    }

    @Override
    public Contact addContact(Contact contact) {
        return contactRepo.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact, Long id) {
        Contact updatedData = new Contact();
        Optional<Contact> data = contactRepo.findById(id);
        if (data.isPresent()) {
            updatedData.setId(data.get().getId());
            updatedData.setName(contact.getName());
            updatedData.setEmail(contact.getEmail());
            updatedData.setPhoneNumber(contact.getPhoneNumber());
            updatedData.setBloodGroup(contact.getBloodGroup());
            updatedData.setTitle(contact.getTitle());
            updatedData.setAddress(contact.getAddress());
            updatedData.setStatus(contact.getStatus());
            updatedData.setPhotoContent(contact.getPhotoContent());
            updatedData.setProfilePicture(contact.getProfilePicture());
            contactRepo.save(updatedData);
        } else {
            throw new RuntimeException("Contact not found.");
        }
        return updatedData;
    }

    @Override
    public void deleteContact(Long id) {
        Optional<Contact> contact = contactRepo.findById(id);
        contact.ifPresent(value -> contactRepo.delete(value));
    }

    @Override
    public void updateProfilepicture(String profilePicture, byte[] photoContent, Long id) throws IOException, SQLException {

        Optional<Contact> contact = contactRepo.findById(id);
        if (contact.isPresent()) {
            Contact updatedData = new Contact();
            updatedData.setId(contact.get().getId());
            updatedData.setName(contact.get().getName());
            updatedData.setEmail(contact.get().getEmail());
            updatedData.setPhoneNumber(contact.get().getPhoneNumber());
            updatedData.setBloodGroup(contact.get().getBloodGroup());
            updatedData.setTitle(contact.get().getTitle());
            updatedData.setAddress(contact.get().getAddress());
            updatedData.setStatus(contact.get().getStatus());
            updatedData.setPhotoContent(photoContent);
            updatedData.setProfilePicture(profilePicture);
            contactRepo.save(updatedData);
        }
    }
}
