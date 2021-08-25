package com.sura.constructores.constructor.repositories;

import com.sura.constructores.constructor.entities.ProjectStatusEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectStatusRepository extends MongoRepository<ProjectStatusEntity, String> {
}
