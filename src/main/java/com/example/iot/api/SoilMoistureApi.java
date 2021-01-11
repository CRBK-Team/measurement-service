package com.example.iot.api;

import com.example.SoilMoistureView;
import com.example.iot.domain.service.SoilMoistureService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/iot/v1/soil-moisture")
public class SoilMoistureApi {
    private final SoilMoistureService soilMoistureService;

    public SoilMoistureApi(SoilMoistureService soilMoistureService) {
        this.soilMoistureService = soilMoistureService;
    }

    @GetMapping
    @ApiOperation("Wyszukiwanie wszystkich pomiarów wilgotności gleby")
    public Set<SoilMoistureView> findAllPageable(Pageable pageable) {
        return soilMoistureService.findAllPageable(pageable);
    }
}
