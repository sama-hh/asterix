package model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("character")
public record Character(String id, String name, int age, String profession) {
}
