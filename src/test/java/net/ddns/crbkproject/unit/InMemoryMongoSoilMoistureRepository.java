package net.ddns.crbkproject.unit;

import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import net.ddns.crbkproject.domain.repository.SoilMoistureRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMongoSoilMoistureRepository implements SoilMoistureRepository {
    Map<ObjectId, MongoSoilMoisture> map = new ConcurrentHashMap<>();

    @Override
    public Page<MongoSoilMoisture> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), pageable.getPageSize());
    }

    @Override
    public MongoSoilMoisture save(MongoSoilMoisture mongoSoilMoisture) {
        map.put(new ObjectId(), mongoSoilMoisture);
        return mongoSoilMoisture;
    }
}
