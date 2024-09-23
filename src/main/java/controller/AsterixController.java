package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.CharacterRepository;

import java.util.List;

@RestController
@RequestMapping("/asterix")
public class AsterixController {
    private final CharacterRepository characterRepository;

    public AsterixController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/characters")
    public List<Character> findAll() {
        List<Character> people = characterRepository.findAll();
        return people;
    }

}
