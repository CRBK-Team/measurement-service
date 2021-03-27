package net.ddns.crbkproject.domain.repository;

import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SoilMoistureRepository {

    Flux<MongoSoilMoisture> findAll(Pageable pageable);

    Mono<MongoSoilMoisture> save(MongoSoilMoisture mongoSoilMoisture);

    Mono<Long> count();
}
