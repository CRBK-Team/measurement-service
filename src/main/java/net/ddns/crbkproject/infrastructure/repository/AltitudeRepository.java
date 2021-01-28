package net.ddns.crbkproject.infrastructure.repository;

import net.ddns.crbkproject.domain.model.Altitude;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AltitudeRepository extends MongoRepository<Altitude, ObjectId> {
}
