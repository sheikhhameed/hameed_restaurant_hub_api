package hameed.restaurant.controller;

import hameed.restaurant.dto.RestaurantRequestDto;
import hameed.restaurant.dto.RestaurantResponseDto;
import hameed.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Restaurant API")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    @Operation(summary = "register new restaurant ", description = "Published a restaurant_registered events on successful to kafka")
    public ResponseEntity<RestaurantResponseDto> create(@Valid @RequestBody RestaurantRequestDto request){

        log.info("POST /api/v1/restaurants/");
        RestaurantResponseDto response =restaurantService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Get ALl restaurants" )
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants(){

        log.info("GET /api/v1/restaurants");
        return ResponseEntity.ok(restaurantService.getAll());

    }
}
