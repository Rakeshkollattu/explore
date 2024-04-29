package com.dev.explore.controller;

import com.dev.explore.entity.Contact;
import com.dev.explore.response.ContactResponse;
import com.dev.explore.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/allContacts")
    public ResponseEntity<Page<Contact>> getAllContacts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(contactService.getAllContacts(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contact>> getContact(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(contactService.getContact(parseLong(id)));
    }

    @PostMapping("/add/newContact")
    public ResponseEntity<Contact> addNewContact(@RequestBody Contact contact) throws SQLException, IOException {
        return ResponseEntity.ok(contactService.addContact(contact));
    }

    @PutMapping("/updateContact/{id}")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable(value = "id") String id) {
        return ResponseEntity.ok(contactService.updateContact(contact, parseLong(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable(value = "id") String id) {
        contactService.deleteContact(parseLong(id));
        return ResponseEntity.ok("Successfully deleted.");
    }

}
