package com.example.demo.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "contacts")
@Data
public class Journal {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;


    public Journal() {
    }
}
