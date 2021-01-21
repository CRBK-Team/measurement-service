package net.ddns.crbkproject.domain.service;

import net.ddns.crbkproject.WeatherView;
import net.ddns.crbkproject.domain.event.WeatherEvent;
import net.ddns.crbkproject.domain.model.Weather;
import net.ddns.crbkproject.infrastructure.repository.WeatherRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final ConversionService mvcConversionService;

    public WeatherService(WeatherRepository weatherRepository, ConversionService mvcConversionService) {
        this.weatherRepository = weatherRepository;
        this.mvcConversionService = mvcConversionService;
    }

    public List<WeatherView> findAllPageable(Pageable pageable) {
        Page<Weather> page = weatherRepository.findAll(pageable);

        List<WeatherView> weatherViews = page.stream()
                .map(weather -> mvcConversionService.convert(weather, WeatherView.class))
                .collect(Collectors.toList());

        return new PageImpl<>(weatherViews, page.getPageable(), page.getTotalElements()).toList();
    }

    public void addWeather(WeatherEvent event) {
        Weather weather = requireNonNull(mvcConversionService.convert(event, Weather.class));
        weatherRepository.save(weather);
    }
}
