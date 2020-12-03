package com.example.iot.domain.repository;

import com.example.iot.domain.model.SoilMoisture;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SoilMoistureRepository extends ElasticsearchRepository<SoilMoisture, UUID> {

}
