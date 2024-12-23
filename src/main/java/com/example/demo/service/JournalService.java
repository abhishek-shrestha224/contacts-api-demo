package com.example.demo.service;

import com.example.demo.entity.Journal;
import com.example.demo.entity.User;
import com.example.demo.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {
    private final JournalRepository journalRepository;
    private final UserService userService;

    @Autowired
    public JournalService(JournalRepository journalRepository, UserService userService) {
        this.journalRepository = journalRepository;
        this.userService = userService;
    }


    public void saveJournal(Journal journal, String userName) {
        User userFind = userService.findByUserName(userName);
        journal.setDate(LocalDateTime.now());
        Journal savedJournal = journalRepository.save(journal);
        userFind.getJournalEntries().add(savedJournal);
        userService.saveUser(userFind);
    }


    public List<Journal> getUserJournals(String userName) {
        User user = userService.findByUserName(userName);
        return user.getJournalEntries();
    }

    public Optional<Journal> getJournalById(ObjectId id) {
        return journalRepository.findById(id);
    }

    public void deleteJournalById(ObjectId id, String userName) {
        User userFind = userService.findByUserName(userName);
        userFind.getJournalEntries().removeIf(journal -> journal.getId().equals(id));
        userService.saveUser(userFind);
        journalRepository.deleteById(id);
    }


}
