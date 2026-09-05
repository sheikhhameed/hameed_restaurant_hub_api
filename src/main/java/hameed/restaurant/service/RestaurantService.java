package hameed.restaurant.service;

import hameed.restaurant.dto.RestaurantRequestDto;
import hameed.restaurant.dto.RestaurantResponseDto;

import java.util.List;

public interface RestaurantService {
    RestaurantResponseDto create(RestaurantRequestDto request);

    List<RestaurantResponseDto> getAll();
}

