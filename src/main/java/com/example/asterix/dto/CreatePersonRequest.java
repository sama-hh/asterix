package com.example.asterix.dto;
import com.example.asterix.model.Character;

public record CreatePersonRequest(String name, int age, String profession) {

    public Character toModel() {
        return Character.builder()
                .name(name)
                .age(age)
                .profession(profession)
                .build();
    }
}
