package net.ddns.crbkproject.infrastructure.repository;

import net.ddns.crbkproject.domain.model.Weather;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, ObjectId> {
}
