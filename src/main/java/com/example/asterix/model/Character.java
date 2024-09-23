package com.example.asterix.model;

import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("character")
@With
@Builder
public record Character(String id, String name, int age, String profession) {
}
