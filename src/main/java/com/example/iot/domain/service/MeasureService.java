package com.example.iot.domain.service;

import com.example.SoilMoistureView;
import com.example.iot.domain.event.SoilMoistureEvent;
import com.example.iot.domain.model.SoilMoisture;
import com.example.iot.domain.repository.SoilMoistureRepository;
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
public class MeasureService {
    private final SoilMoistureRepository soilMoistureRepository;
    private final ConversionService conversionService;

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

    public void addSoilMoisture(SoilMoistureEvent event) {
        SoilMoisture soilMoisture = requireNonNull(conversionService.convert(event, SoilMoisture.class));
        soilMoistureRepository.save(soilMoisture);
    }
}
