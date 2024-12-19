package com.example.demo.controller;


import com.example.demo.entity.Contact;
import com.example.demo.service.ContactService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        contact.setDate(LocalDateTime.now());
        contactService.saveContact(contact);
        return contact;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable("id") ObjectId id) {
        return contactService.getContactById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public boolean deleteContactById(@PathVariable("id") ObjectId id) {
        contactService.deleteContactById(id);
        return true;
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable("id") ObjectId id, @RequestBody Contact contact) {
        Contact find = contactService.getContactById(id).orElse(null);
        if (find == null) {
            return null;
        }

        find.setName(contact.getName() != null && !contact.getName().isEmpty() ? contact.getName() : find.getName());
        find.setPhone(contact.getPhone() != null && !contact.getPhone().isEmpty() ? contact.getPhone() : find.getPhone());
        find.setEmail(contact.getEmail() != null && !contact.getEmail().isEmpty() ? contact.getEmail() : find.getEmail());
        find.setDate(LocalDateTime.now());
        contactService.saveContact(find);
        return find;
    }
}
