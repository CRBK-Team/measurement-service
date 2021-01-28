package net.ddns.crbkproject.domain.service;

import net.ddns.crbkproject.TemperatureView;
import net.ddns.crbkproject.domain.model.Temperature;
import net.ddns.crbkproject.infrastructure.repository.TemperatureRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;
    private final ConversionService mvcConversionService;

    public TemperatureService(TemperatureRepository temperatureRepository, ConversionService mvcConversionService) {
        this.temperatureRepository = temperatureRepository;
        this.mvcConversionService = mvcConversionService;
    }

    public List<TemperatureView> findAllPageable(Pageable pageable) {
        Page<Temperature> page = temperatureRepository.findAll(pageable);

        List<TemperatureView> temperatureViews = page.stream()
                .map(weather -> mvcConversionService.convert(weather, TemperatureView.class))
                .collect(Collectors.toList());

        return new PageImpl<>(temperatureViews, page.getPageable(), page.getTotalElements()).toList();
    }
}
