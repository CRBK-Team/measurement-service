package net.ddns.crbkproject.infrastructure.repository;

import net.ddns.crbkproject.domain.model.Humidity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumidityRepository extends MongoRepository<Humidity, ObjectId> {
}
