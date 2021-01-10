package com.example.iot.domain.service;

import com.example.SoilMoistureView;
import com.example.iot.domain.event.SoilMoistureEvent;
import com.example.iot.domain.model.SoilMoisture;
import com.example.iot.domain.model.SoilMoistureRawInflux;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
public class MeasureService {
    private final InfluxDBClient influxDBClient;
    private final ConversionService conversionService;

    public MeasureService(InfluxDBClient influxDBClient, @Qualifier("mvcConversionService") ConversionService conversionService) {
        this.influxDBClient = influxDBClient;
        this.conversionService = conversionService;
    }

    public Set<SoilMoistureView> findLastMeasures() {
        List<SoilMoistureRawInflux> rawSoilMoisture = influxDBClient.getQueryApi().query(
                "from(bucket: \"sensors\")\n" +
                        "  |> range(start: -24h)\n" +
                        "  |> filter(fn: (r) => r[\"_measurement\"] == \"soil-moisture\")\n", SoilMoistureRawInflux.class);

        return rawSoilMoisture.stream()
                .map(raw -> conversionService.convert(raw, SoilMoisture.class))
                .map(soilMoisture -> conversionService.convert(soilMoisture, SoilMoistureView.class))
                .collect(Collectors.toSet());
    }

    public void addSoilMoisture(SoilMoistureEvent event) {
        SoilMoisture soilMoisture = requireNonNull(conversionService.convert(event, SoilMoisture.class));
        save(soilMoisture);

        conversionService.convert(soilMoisture, SoilMoistureView.class);
    }

    private void save(SoilMoisture soilMoisture) {
        SoilMoistureRawInflux rawSoilMoisture = conversionService.convert(soilMoisture, SoilMoistureRawInflux.class);
        influxDBClient.getWriteApi().writeMeasurement("sensors", "primergy", WritePrecision.MS, rawSoilMoisture);
    }
}
