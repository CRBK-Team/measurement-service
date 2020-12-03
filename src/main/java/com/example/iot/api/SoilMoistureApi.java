package com.example.iot.api;

import com.example.SoilMoistureView;
import com.example.iot.domain.service.MeasureService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/v1/soil-moisture")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class SoilMoistureApi {
    MeasureService measureService;

    @GetMapping
    public Page<SoilMoistureView> findAllPageable(Pageable pageable) {
        return measureService.findAllPageable(pageable);
    }
}
