package com.sura.constructores.constructor.repositories;

import com.sura.constructores.constructor.entities.MaterialEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaterialRepository extends MongoRepository<MaterialEntity, String> {
    MaterialEntity findByType(String type);

}
