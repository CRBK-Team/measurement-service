package net.ddns.crbkproject.unit;

import net.ddns.crbkproject.infrastructure.converter.EventToMongoSoilMoistureConverter;
import net.ddns.crbkproject.domain.converter.MongoToSoilMoistureConverter;
import net.ddns.crbkproject.domain.repository.SoilMoistureRepository;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

public class UnitTestConfiguration {

    public static GenericConversionService conversionService() {
        GenericConversionService conversionService = new ApplicationConversionService();
        getConverters().forEach(conversionService::addConverter);

        return conversionService;
    }

    public static SoilMoistureRepository soilMoistureRepository() {
        return new InMemoryMongoSoilMoistureRepository();
    }

    private static Set<Converter<?, ?>> getConverters() {
        return Set.of(
                new EventToMongoSoilMoistureConverter(),
                new MongoToSoilMoistureConverter());
    }
}
