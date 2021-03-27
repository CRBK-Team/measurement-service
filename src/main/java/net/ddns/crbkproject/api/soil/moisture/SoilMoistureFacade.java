package net.ddns.crbkproject.api.soil.moisture;

import net.ddns.crbkproject.api.PaginatedResponse;
import net.ddns.crbkproject.application.SoilMoistureService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoilMoistureFacade {
    private final SoilMoistureService soilMoistureService;

    public SoilMoistureFacade(SoilMoistureService soilMoistureService) {
        this.soilMoistureService = soilMoistureService;
    }

    public Mono<PaginatedResponse<SoilMoistureResponse>> getPage(Pageable pageable) {
        return soilMoistureService.findAllPageable(pageable)
                .flatMap(page -> {
                    List<SoilMoistureResponse> content = page.getContent().stream()
                            .map(SoilMoistureResponse::of)
                            .collect(Collectors.toList());
                    return Mono.just(new PaginatedResponse<>(content, page));
                });
    }
}
