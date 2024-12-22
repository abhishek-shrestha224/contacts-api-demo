package com.example.demo.controller;


import com.example.demo.entity.Journal;
import com.example.demo.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journals")
public class JournalController {
    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping
    public Journal createContact(@RequestBody Journal contact) {
        contact.setDate(LocalDateTime.now());
        journalService.saveContact(contact);
        return contact;
    }

    @GetMapping
    public List<Journal> getAllContacts() {
        return journalService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journal> getContactById(@PathVariable("id") ObjectId id) {
        Optional<Journal> contact = journalService.getContactById(id);
        return contact.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public boolean deleteContactById(@PathVariable("id") ObjectId id) {
        journalService.deleteContactById(id);
        return true;
    }

    @PutMapping("/{id}")
    public Journal updateContact(@PathVariable("id") ObjectId id, @RequestBody Journal contact) {
        Journal find = journalService.getContactById(id).orElse(null);
        if (find == null) {
            return null;
        }

        find.setTitle(contact.getTitle() != null && !contact.getTitle().isEmpty() ? contact.getTitle() : find.getTitle());
        find.setContent(contact.getContent() != null && !contact.getContent().isEmpty() ? contact.getContent() : find.getContent());
        find.setDate(LocalDateTime.now());
        journalService.saveContact(find);
        return find;
    }
}
