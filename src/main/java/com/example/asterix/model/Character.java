package com.example.asterix.model;

import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("character")
@With
public record Character(String id, String name, int age, String profession) {
}
