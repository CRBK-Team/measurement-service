package net.ddns.crbkproject.infrastructure.mongo.repository;

import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MongoSoilMoistureRepository extends ReactiveMongoRepository<MongoSoilMoisture, ObjectId> {

    @Query("{ id: { $exists: true } }")
    Flux<MongoSoilMoisture> findAll(Pageable pageable);
}
