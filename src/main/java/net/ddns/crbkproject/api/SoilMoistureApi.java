package net.ddns.crbkproject.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.ddns.crbkproject.SoilMoistureView;
import net.ddns.crbkproject.domain.service.SoilMoistureService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/soil-moisture")
public class SoilMoistureApi {
    private final SoilMoistureService soilMoistureService;

    public SoilMoistureApi(SoilMoistureService soilMoistureService) {
        this.soilMoistureService = soilMoistureService;
    }

    @GetMapping
    @ApiOperation("Wyszukiwanie wszystkich pomiarów wilgotności gleby")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "numer bieżącej strony"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "rozmiar stron"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "parametry sortowania")})
    public List<SoilMoistureView> findAllPageable(@SortDefault(sort = "measuredAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return soilMoistureService.findAllPageable(pageable);
    }
}
