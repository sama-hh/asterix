package com.example.asterix.controller;

import com.example.asterix.dto.CreatePersonRequest;
import com.example.asterix.dto.UpdateCharacterRequest;
import com.example.asterix.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.asterix.repository.CharacterRepository;
import com.example.asterix.model.Character;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asterix")
@RequiredArgsConstructor
public class AsterixController {
    private final CharacterRepository characterRepository;
    private final CharacterService characterService;

    @GetMapping("/characters")
    public List<Character> findAll(@RequestParam(required = false) Integer age) {
        return characterService.getAllCharacters(age);
    }


//    @GetMapping("/characters")
//    public List<Character> findAll(@RequestParam("name") String name) {
//        if (name == null) {
//            return characterRepository.findAll();
//        }
//        List<Character> peopleByName = characterRepository.findByName(name);
//        return peopleByName;
//    }

    @PostMapping("characters")
    public Character save(@RequestBody CreatePersonRequest request) {
        Character newCharacter = characterService.createCharacter(request.toModel());
        return newCharacter;
    }



    @DeleteMapping("characters/{id}")
    public void delete(@PathVariable String id) {
        characterService.deleteCharacter(id);
    }

//    @PutMapping("characters/{id}")
//    public Character update(@PathVariable String id, @RequestBody Character character) {
//        Character existingCharacter = characterRepository.findById(id)
//                .orElse(null);
//        characterRepository.deleteById(id);
//        Character newCharacter = character.withId(id);
//        characterRepository.save(newCharacter);
//        return newCharacter;
//    }

    @PutMapping("characters/{id}")
    public Character updateCharacter(@PathVariable String id, @RequestBody UpdateCharacterRequest request) {
        return characterService.updateCharacter(id, request);
    }

}
