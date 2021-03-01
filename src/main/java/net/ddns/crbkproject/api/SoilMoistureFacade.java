package net.ddns.crbkproject.api;

import net.ddns.crbkproject.application.SoilMoistureService;
import net.ddns.crbkproject.domain.model.measurement.SoilMoisture;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoilMoistureFacade {
    private final SoilMoistureService soilMoistureService;

    public SoilMoistureFacade(SoilMoistureService soilMoistureService) {
        this.soilMoistureService = soilMoistureService;
    }

    public List<SoilMoistureResponse> getPage(Pageable pageable) {
        List<SoilMoisture> soilMoistureList = soilMoistureService.findAllPageable(pageable);

        return soilMoistureList.stream()
                .map(SoilMoistureResponse::of)
                .collect(Collectors.toList());
    }
}
