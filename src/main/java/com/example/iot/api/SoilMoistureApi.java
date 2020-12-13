package com.example.iot.api;

import com.example.SoilMoistureView;
import com.example.iot.domain.service.MeasureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/soil-moisture")
public class SoilMoistureApi {
    private final MeasureService measureService;

    public SoilMoistureApi(MeasureService measureService) {
        this.measureService = measureService;
    }

    @GetMapping
    public Page<SoilMoistureView> findAllPageable(Pageable pageable) {
        return measureService.findAllPageable(pageable);
    }
}
