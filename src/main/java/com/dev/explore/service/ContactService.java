package com.dev.explore.service;

import com.dev.explore.entity.Contact;
import com.dev.explore.response.ContactResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface ContactService {

    Page<Contact> getAllContacts(int pageNumber, int pageSize);

    Optional<Contact> getContact(Long id);

    Contact addContact(Contact contact);

    Contact updateContact(Contact contact, Long id);

    void deleteContact(Long id);

    void updateProfilepicture(String profilePicture, byte[] photoContent,Long id) throws IOException, SQLException;
}
