package com.example.asterix.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.asterix.model.Character;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {

    @Query("{name: ?0}")
    List<Character> findByName(String name);

    List<Character> findByAgeLessThanEqual(int age);

}
