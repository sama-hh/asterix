package repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class CharacterRepository implements MongoRepository<Character, String> {

}
