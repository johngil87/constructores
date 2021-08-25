package com.sura.constructores.constructor.repositories;

import com.sura.constructores.constructor.entities.BuildOrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuildOrderRepository extends MongoRepository<BuildOrderEntity, String> {
}
