package com.example.demo.controller;

import com.example.demo.entity.Contact;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final Map<Integer, Contact> contacts = new HashMap<>();


    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contacts.put(contact.getId(), contact);
    }

    @GetMapping
    public List<Contact> showContacts() {
        return new ArrayList<>(contacts.values());
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable("id") int id) {
        return contacts.get(id);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable("id") int id, @RequestBody Contact contact) {
        return contacts.put(id, contact);
    }

    @DeleteMapping("/{id}")
    public Contact deleteContact(@PathVariable("id") int id) {
        return contacts.remove(id);
    }
}
