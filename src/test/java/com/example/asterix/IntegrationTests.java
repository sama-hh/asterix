package com.example.asterix;

import com.example.asterix.model.Character;
import com.example.asterix.repository.CharacterRepository;
import com.example.asterix.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterRepository characterRepository;

    @Test
    @DirtiesContext
    void getAllCharacters() throws Exception {
        Character character = new Character("1", "Anna", 23, "Teacher");
        characterRepository.save(character);
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json(
            """
                           [
                            {
                               "id": "1",
                               "name": "Anna",
                               "age": 23,
                               "profession": "Teacher"
                            }
                           ]
                       """
                ));
    }

    @Test
    @DirtiesContext
    void addCharacter() throws Exception {
        String character = """
            {
                "name": "Anna",
                "age": 23,
                "profession": "Teacher"
            }
        """;
        mockMvc.perform(MockMvcRequestBuilders.post("/asterix/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(character))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(character));
    }

    @Test
    @DirtiesContext
    void updateCharacter() throws Exception {
        Character character = new Character("1", "Anna", 23, "Teacher");
        characterRepository.save(character);

        String updatedCharacter = """
            {
                "id": "1",
                "name": "Anna Smith",
                "age": 24,
                "profession": "Professor"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.put("/asterix/characters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedCharacter))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(updatedCharacter));
    }

    @Test
    @DirtiesContext
    void deleteCharacter() throws Exception {
        Character character = new Character("1", "Anna", 23, "Teacher");
        characterRepository.save(character);

        mockMvc.perform(MockMvcRequestBuilders.delete("/asterix/characters/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
