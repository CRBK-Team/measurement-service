package net.ddns.crbkproject.application;

import static java.util.Objects.requireNonNull;
import net.ddns.crbkproject.domain.model.common.Measurement;
import net.ddns.crbkproject.domain.model.measurement.SoilMoisture;
import net.ddns.crbkproject.domain.model.measurement.TestMeasure;
import net.ddns.crbkproject.domain.repository.SoilMoistureRepository;
import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import net.ddns.crbkproject.infrastructure.mongo.model.MongoTestMeasure;
import net.ddns.crbkproject.infrastructure.mongo.repository.MongoTestMeasureRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoilMoistureService {
    private final SoilMoistureRepository soilMoistureRepository;
    private final MongoTestMeasureRepository testMeasureRepository;
    private final ConversionService conversionService;

    public SoilMoistureService(SoilMoistureRepository soilMoistureRepository, MongoTestMeasureRepository testMeasureRepository, ConversionService conversionService) {
        this.soilMoistureRepository = soilMoistureRepository;
        this.testMeasureRepository = testMeasureRepository;
        this.conversionService = conversionService;
    }

    public List<SoilMoisture> findAllPageable(Pageable pageable) {
        Page<MongoSoilMoisture> page = soilMoistureRepository.findAll(pageable);

        List<SoilMoisture> mongoSoilMoistureList = page.stream()
                .map(mongoSoilMoisture -> conversionService.convert(mongoSoilMoisture, SoilMoisture.class))
                .collect(Collectors.toList());

        return new PageImpl<>(mongoSoilMoistureList, page.getPageable(), page.getTotalElements()).toList();
    }

    public SoilMoisture add(Measurement measurement) {
        MongoSoilMoisture mongoSoilMoisture = requireNonNull(conversionService.convert(measurement, MongoSoilMoisture.class));
        mongoSoilMoisture = soilMoistureRepository.save(mongoSoilMoisture);

        return requireNonNull(conversionService.convert(mongoSoilMoisture, SoilMoisture.class));
    }

    public void addTest(Measurement measurement) {
        MongoTestMeasure mongoTestMeasure = requireNonNull(conversionService.convert(measurement, MongoTestMeasure.class));
        testMeasureRepository.save(mongoTestMeasure);
    }
}
