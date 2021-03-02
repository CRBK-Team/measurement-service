package net.ddns.crbkproject.infrastructure;

import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import net.ddns.crbkproject.domain.repository.SoilMoistureRepository;
import net.ddns.crbkproject.infrastructure.mongo.repository.MongoSoilMoistureRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
class SoilMoistureRepositoryImpl implements SoilMoistureRepository {
    private final MongoSoilMoistureRepository mongoRepository;

    public SoilMoistureRepositoryImpl(MongoSoilMoistureRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Page<MongoSoilMoisture> findAll(Pageable pageable) {
        return mongoRepository.findAll(pageable);
    }

    @Override
    public MongoSoilMoisture save(MongoSoilMoisture mongoSoilMoisture) {
        return mongoRepository.save(mongoSoilMoisture);
    }
}
