package com.example.asterix.service;

import com.example.asterix.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.asterix.model.Character;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final IdService IDservice;

    public Character createCharacter(Character character) {
        String generatedId = IDservice.randomId();

        Character newCharacter = new Character(generatedId, character.name(), character.age(), character.profession());

        validateCharacter(newCharacter);
        return characterRepository.save(newCharacter);
    }

    public List<Character> getAllCharacters(Integer age) {
        List<Character> filteredCharacters = characterRepository.findByAgeLessThanEqual(age);

        return filteredCharacters;
    }


    private void validateCharacter(Character character) {
        validateName(character.name());
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
