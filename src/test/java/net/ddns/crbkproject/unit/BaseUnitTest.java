package net.ddns.crbkproject.unit;

import net.ddns.crbkproject.application.SoilMoistureService;
import net.ddns.crbkproject.domain.repository.SoilMoistureRepository;
import net.ddns.crbkproject.infrastructure.mongo.repository.MongoSoilMoistureRepository;
import org.springframework.core.convert.ConversionService;

import static net.ddns.crbkproject.unit.UnitTestConfiguration.conversionService;
import static net.ddns.crbkproject.unit.UnitTestConfiguration.soilMoistureRepository;

abstract class BaseUnitTest {

    ConversionService conversionService = conversionService();
    SoilMoistureRepository soilMoistureRepository = soilMoistureRepository();
    SoilMoistureService soilMoistureService = new SoilMoistureService(soilMoistureRepository, conversionService);
}
