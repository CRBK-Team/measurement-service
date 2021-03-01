package net.ddns.crbkproject.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/soil-moisture")
class SoilMoistureController {
    private final SoilMoistureFacade facade;

    public SoilMoistureController(SoilMoistureFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    @Operation(summary = "Wyszukiwanie wszystkich pomiarów wilgotności gleby")
    public List<SoilMoistureResponse> getPage(@SortDefault(sort = "measuredAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return facade.getPage(pageable);
    }
}
