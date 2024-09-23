package com.example.asterix.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.asterix.model.Character;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
}
