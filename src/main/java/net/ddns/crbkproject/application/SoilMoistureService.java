package net.ddns.crbkproject.application;

import net.ddns.crbkproject.domain.model.common.Measurement;
import net.ddns.crbkproject.domain.model.measurement.SoilMoisture;
import net.ddns.crbkproject.domain.repository.SoilMoistureRepository;
import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static java.util.Objects.requireNonNull;

@Service
public class SoilMoistureService {
    private final SoilMoistureRepository soilMoistureRepository;
    private final ConversionService conversionService;

    public SoilMoistureService(SoilMoistureRepository soilMoistureRepository, ConversionService conversionService) {
        this.soilMoistureRepository = soilMoistureRepository;
        this.conversionService = conversionService;
    }

    public Mono<PageImpl<SoilMoisture>> findAllPageable(Pageable pageable) {
        return soilMoistureRepository.count()
                .flatMap(totalElements -> soilMoistureRepository.findAll(pageable)
                        .map(mongoSoilMoisture -> requireNonNull(conversionService.convert(mongoSoilMoisture, SoilMoisture.class)))
                        .collectList()
                        .map(soilMoistureList -> new PageImpl<>(soilMoistureList, pageable, totalElements)));
    }

    public Mono<SoilMoisture> add(Measurement measurement) {
        MongoSoilMoisture mongoSoilMoisture = requireNonNull(conversionService.convert(measurement, MongoSoilMoisture.class));

        return soilMoistureRepository.save(mongoSoilMoisture)
                .map(savedMongoSoilMoisture -> requireNonNull(conversionService.convert(savedMongoSoilMoisture, SoilMoisture.class)));
    }
}
