package net.ddns.crbkproject.infrastructure.repository;

import net.ddns.crbkproject.domain.model.Pressure;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PressureRepository extends MongoRepository<Pressure, ObjectId> {
}
