package com.example.demo.controller;


import com.example.demo.entity.Journal;
import com.example.demo.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journals")
public class JournalController {
    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping("/{userName}")
    public Journal createJournal(@RequestBody Journal journal, @PathVariable("userName") String userName) {
        journalService.saveJournal(journal, userName);
        return journal;
    }

    @GetMapping("/{userName}")
    public List<Journal> getAllJournals(@PathVariable("userName") String userName) {
        return journalService.getUserJournals(userName);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable("id") ObjectId id) {
        Optional<Journal> journal = journalService.getJournalById(id);
        return journal.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public boolean deleteJournalById(@PathVariable("id") ObjectId id) {
        journalService.deleteJournalById(id);
        return true;
    }

//    @PutMapping("/{id}")
//    public Journal updateJournal(@PathVariable("id") ObjectId id, @RequestBody Journal journal) {
//        Journal find = journalService.getJournalById(id).orElse(null);
//        if (find == null) {
//            return null;
//        }
//
//        find.setTitle(!journal.getTitle().isEmpty() ? journal.getTitle() : find.getTitle());
//        find.setContent(journal.getContent() != null && !journal.getContent().isEmpty() ? journal.getContent() : find.getContent());
//        find.setDate(LocalDateTime.now());
//        journalService.saveJournal(find);
//        return find;
//    }
}
