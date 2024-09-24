package com.example.asterix;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.asterix.controller.AsterixController;
import com.example.asterix.dto.UpdateCharacterRequest;
import com.example.asterix.model.Character;
import com.example.asterix.repository.CharacterRepository;
import com.example.asterix.service.CharacterService;
import com.example.asterix.service.IdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class AsterixApplicationTests {

	@Mock
	private CharacterRepository characterRepository;

	@Mock
	private IdService IdService;

	@Mock
	private CharacterService characterService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		characterService = new CharacterService(characterRepository, IdService);
	}

	@Test
	public void findAllCharactersWhenAgeIsNull() {
		// Given
		List<Character> characters = Arrays.asList(
				new Character("1", "Idefix", 5, "Hund"),
				new Character("2", "Anna", 25, "Barden")
		);

		when(characterRepository.findAll()).thenReturn(characters);

		// When
		List<Character> result = characterService.getAllCharacters(null);

		// Then
		assertEquals(2, result.size());
		verify(characterRepository, times(1)).findAll();
		verify(characterRepository, never()).findByAgeLessThanEqual(anyInt());
	}

	@Test
	public void findAllCharactersWhenAgeIsSpecified() {
		// Given
		List<Character> characters = Arrays.asList(
				new Character("1", "Idefix", 5, "Hund")
		);

		int age = 30;

		when(characterRepository.findByAgeLessThanEqual(age)).thenReturn(characters);

		// When
		List<Character> result = characterService.getAllCharacters(age);

		// Then
		assertEquals(1, result.size());
		verify(characterRepository, times(1)).findByAgeLessThanEqual(age);
		verify(characterRepository, never()).findAll();
	}

	@Test
	public void updateCharacter() {
		// Given
		String characterId = "1";
		Character existingCharacter = new Character(characterId, "Idefix", 5, "Hund");
		UpdateCharacterRequest request = new UpdateCharacterRequest("Idefix", 6, "Hund");

		when(characterRepository.findById(characterId)).thenReturn(Optional.of(existingCharacter));
		when(characterRepository.save(any(Character.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Character result = characterService.updateCharacter(characterId, request);

		// Then
		assertEquals(request.name(), result.name());
		assertEquals(request.age(), result.age());
		assertEquals(request.profession(), result.profession());
		verify(characterRepository, times(1)).findById(characterId);
		verify(characterRepository, times(1)).save(any(Character.class));
	}

	@Test
	public void deleteCharacter() {
		// Given
		String characterId = "1";
		when(characterRepository.existsById(characterId)).thenReturn(true);

		// When
		characterService.deleteCharacter(characterId);

		// Then
		verify(characterRepository, times(1)).deleteById(characterId);
	}

	@Test
	public void addCharacter() {
		String generatedId = "1";
		Character character = Character.builder()
				.name("Idefix")
				.age(5)
				.profession("Dog")
				.build();

		when(IdService.randomId()).thenReturn(generatedId);
		when(characterRepository.save(any(Character.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// When
		Character result = characterService.createCharacter(character);

		// Then
		assertEquals(generatedId, result.id());
		assertEquals("Idefix", result.name());
		assertEquals(5, result.age());
		assertEquals("Dog", result.profession());
		verify(IdService, times(1)).randomId();
		verify(characterRepository, times(1)).save(any(Character.class));
	}
}
