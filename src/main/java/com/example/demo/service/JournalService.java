package com.example.demo.service;

import com.example.demo.entity.Journal;
import com.example.demo.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {
    private final JournalRepository journalRepository;

    @Autowired
    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }


    public void saveContact(Journal contact) {
        journalRepository.save(contact);
    }

    public List<Journal> getAllContacts() {
        return journalRepository.findAll();
    }

    public Optional<Journal> getContactById(ObjectId id) {
        return journalRepository.findById(id);
    }

    public void deleteContactById(ObjectId id) {
        journalRepository.deleteById(id);
    }


}
