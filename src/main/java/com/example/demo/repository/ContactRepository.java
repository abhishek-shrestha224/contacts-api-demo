package com.example.demo.repository;

import com.example.demo.entity.Contact;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, ObjectId> {
}

// controller --> service --> repository