package net.ddns.crbkproject.domain.service;

import net.ddns.crbkproject.SoilMoistureView;
import net.ddns.crbkproject.domain.event.SoilMoistureEvent;
import net.ddns.crbkproject.domain.model.SoilMoisture;
import net.ddns.crbkproject.infrastructure.repository.SoilMoistureRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
public class SoilMoistureService {
    private final SoilMoistureRepository soilMoistureRepository;
    private final ConversionService mvcConversionService;

    public SoilMoistureService(SoilMoistureRepository soilMoistureRepository, ConversionService mvcConversionService) {
        this.soilMoistureRepository = soilMoistureRepository;
        this.mvcConversionService = mvcConversionService;
    }

    public List<SoilMoistureView> findAllPageable(Pageable pageable) {
        Page<SoilMoisture> page = soilMoistureRepository.findAll(pageable);

        List<SoilMoistureView> soilMoistureViews = page.stream()
                .map(soilMoisture -> mvcConversionService.convert(soilMoisture, SoilMoistureView.class))
                .collect(Collectors.toList());

        return new PageImpl<>(soilMoistureViews, page.getPageable(), page.getTotalElements()).toList();
    }

    public void addSoilMoisture(SoilMoistureEvent event) {
        @Valid SoilMoisture soilMoisture = requireNonNull(mvcConversionService.convert(event, SoilMoisture.class));
        soilMoistureRepository.save(soilMoisture);
    }
}
