package com.sura.constructores.constructor.repositories;

import com.sura.constructores.constructor.entities.BuildOrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BuildOrderRepository extends MongoRepository<BuildOrderEntity, String> {
    Optional <BuildOrderEntity> findByCoordinateXAndCoordinateY(Double coordinateX, Double coordinateY);
    List<BuildOrderEntity> findAllByStatus(String status);
}
