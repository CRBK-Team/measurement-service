package com.example.iot.domain.service;

import com.example.SoilMoistureView;
import com.example.iot.domain.event.SoilMoistureMeasuredEvent;
import com.example.iot.domain.model.SoilMoisture;
import com.example.iot.domain.repository.SoilMoistureRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MeasureService {
    SoilMoistureRepository soilMoistureRepository;
    ConversionService conversionService;

    public MeasureService(SoilMoistureRepository soilMoistureRepository, @Qualifier("mvcConversionService") ConversionService conversionService) {
        this.soilMoistureRepository = soilMoistureRepository;
        this.conversionService = conversionService;
    }

    public Page<SoilMoistureView> findAllPageable(Pageable pageable) {
        Page<SoilMoisture> page = soilMoistureRepository.findAll(pageable);

        List<SoilMoistureView> soilMoistureViews = page.stream()
                .map(soilMoisture -> conversionService.convert(soilMoisture, SoilMoistureView.class))
                .collect(Collectors.toList());

        return new PageImpl<>(soilMoistureViews, page.getPageable(), page.getTotalElements());
    }

    public void addSoilMoisture(SoilMoistureMeasuredEvent event) {
        SoilMoisture soilMoisture = requireNonNull(conversionService.convert(event, SoilMoisture.class));
        soilMoistureRepository.save(soilMoisture);
    }
}
