package net.ddns.crbkproject.infrastructure.mongo.repository;

import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoSoilMoistureRepository extends MongoRepository<MongoSoilMoisture, ObjectId> {
}
