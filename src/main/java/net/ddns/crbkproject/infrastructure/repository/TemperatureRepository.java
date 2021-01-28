package net.ddns.crbkproject.infrastructure.repository;

import net.ddns.crbkproject.domain.model.Temperature;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends MongoRepository<Temperature, ObjectId> {
}
