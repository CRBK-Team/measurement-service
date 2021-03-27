package net.ddns.crbkproject.infrastructure;

import net.ddns.crbkproject.domain.repository.SoilMoistureRepository;
import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import net.ddns.crbkproject.infrastructure.mongo.repository.MongoSoilMoistureRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
class SoilMoistureRepositoryImpl implements SoilMoistureRepository {
    private final MongoSoilMoistureRepository mongoRepository;

    public SoilMoistureRepositoryImpl(MongoSoilMoistureRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Flux<MongoSoilMoisture> findAll(Pageable pageable) {
        return mongoRepository.findAll(pageable);
    }

    @Override
    public Mono<MongoSoilMoisture> save(MongoSoilMoisture mongoSoilMoisture) {
        return mongoRepository.save(mongoSoilMoisture);
    }

    @Override
    public Mono<Long> count() {
        return mongoRepository.count();
    }
}
