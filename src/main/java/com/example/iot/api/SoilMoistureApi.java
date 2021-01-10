package com.example.iot.api;

import com.example.SoilMoistureView;
import com.example.iot.domain.service.MeasureService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/iot/v1/soil-moisture")
public class SoilMoistureApi {
    private final MeasureService measureService;

    public SoilMoistureApi(MeasureService measureService) {
        this.measureService = measureService;
    }

    @GetMapping
    @ApiOperation("Wyszukiwanie pomiarów wilgotności gleby z ostatnich 24h")
    public Set<SoilMoistureView> findLastMeasures() {
        return measureService.findLastMeasures();
    }
}
