package com.example.iot.api;

import com.example.SoilMoistureView;
import com.example.iot.domain.service.MeasureService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iot/v1/soil-moisture")
public class SoilMoistureApi {
    private final MeasureService measureService;

    public SoilMoistureApi(MeasureService measureService) {
        this.measureService = measureService;
    }

    @GetMapping
    @ApiOperation("Wyszukiwanie wszystkich pomiarów wilgotności gleby")
    public Page<SoilMoistureView> findAllPageable(Pageable pageable) {
        return measureService.findAllPageable(pageable);
    }
}
