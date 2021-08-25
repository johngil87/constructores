package com.sura.constructores.constructor.repositories;

import com.sura.constructores.constructor.entities.BuildingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuildingRepository extends MongoRepository<BuildingEntity, String> {
}
