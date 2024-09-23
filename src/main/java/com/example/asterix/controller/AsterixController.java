package com.example.asterix.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.asterix.repository.CharacterRepository;
import com.example.asterix.model.Character;

import java.util.List;

@RestController
@RequestMapping("/asterix")
@RequiredArgsConstructor
public class AsterixController {
    private final CharacterRepository characterRepository;

//    @GetMapping("/characters")
//    public List<Character> findAll() {
//        List<Character> people = characterRepository.findAll();
//        return people;
//    }

    @GetMapping("/characters")
    public List<Character> findAll(@RequestParam("name") String name) {
        if (name == null) {
            return characterRepository.findAll();
        }
        List<Character> peopleByName = characterRepository.findByName(name);
        return peopleByName;
    }

    @PostMapping("characters")
    public Character save(@RequestBody Character character) {
        Character saved = characterRepository.save(character);
        return saved;
    }

    @DeleteMapping("characters/{id}")
    public void delete(@PathVariable String id) {
        characterRepository.deleteById(id);
    }

    @PutMapping("characters/{id}")
    public Character update(@PathVariable String id, @RequestBody Character character) {
        Character existingCharacter = characterRepository.findById(id)
                .orElse(null);
        characterRepository.deleteById(id);
        Character newCharacter = character.withId(id);
        characterRepository.save(newCharacter);
        return newCharacter;
    }

}
