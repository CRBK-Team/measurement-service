package net.ddns.crbkproject.unit;

import net.ddns.crbkproject.application.SoilMoistureService;
import net.ddns.crbkproject.domain.repository.SoilMoistureRepository;
import org.springframework.core.convert.ConversionService;

import static net.ddns.crbkproject.unit.UnitTestConfiguration.conversionService;
import static net.ddns.crbkproject.unit.UnitTestConfiguration.soilMoistureRepository;

public abstract class BaseUnitTest {
    protected ConversionService conversionService = conversionService();
    protected SoilMoistureRepository soilMoistureRepository = soilMoistureRepository();
    protected SoilMoistureService soilMoistureService = new SoilMoistureService(soilMoistureRepository, conversionService);
}
