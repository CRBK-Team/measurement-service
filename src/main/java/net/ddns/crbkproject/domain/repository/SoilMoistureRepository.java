package net.ddns.crbkproject.domain.repository;

import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SoilMoistureRepository {

    Page<MongoSoilMoisture> findAll(Pageable pageable);

    MongoSoilMoisture save(MongoSoilMoisture mongoSoilMoisture);
}
