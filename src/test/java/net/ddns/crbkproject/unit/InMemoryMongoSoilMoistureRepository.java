package net.ddns.crbkproject.unit;

import net.ddns.crbkproject.domain.repository.SoilMoistureRepository;
import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMongoSoilMoistureRepository implements SoilMoistureRepository {
    Map<ObjectId, MongoSoilMoisture> map = new ConcurrentHashMap<>();

    @Override
    public Flux<MongoSoilMoisture> findAll(Pageable pageable) {
        return Flux.fromIterable(new PageImpl<>(new ArrayList<>(map.values()),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), pageable.getPageSize()).getContent());
    }

    @Override
    public Mono<MongoSoilMoisture> save(MongoSoilMoisture mongoSoilMoisture) {
        map.put(ObjectId.get(), mongoSoilMoisture);
        return Mono.just(mongoSoilMoisture);
    }

    @Override
    public Mono<Long> count() {
        return Mono.just((long) map.values().size());
    }
}
