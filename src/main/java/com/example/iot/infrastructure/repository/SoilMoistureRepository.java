package com.example.iot.infrastructure.repository;

import com.example.iot.domain.model.SoilMoisture;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilMoistureRepository extends MongoRepository<SoilMoisture, ObjectId> {
}
