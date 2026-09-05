package hameed.restaurant.serviceImpl;

import hameed.restaurant.dto.RestaurantRequestDto;
import hameed.restaurant.dto.RestaurantResponseDto;
import hameed.restaurant.event.RestaurantRegisteredEvent;
import hameed.restaurant.mapper.RestaurantMapper;
import hameed.restaurant.messaging.RestaurantEventPublisher;
import hameed.restaurant.model.RegisterRestaurant;
import hameed.restaurant.repository.RestaurantRepository;
import hameed.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper mapper;

    private final RestaurantEventPublisher eventPublisher;

    @Override
    @Transactional
    public RestaurantResponseDto create(RestaurantRequestDto request) {
        log.info("Creating restaurants: {}", request.name());

        restaurantRepository.findByPhone(request.phone())
                .ifPresent(r -> {
            throw new IllegalArgumentException("phone already registered:"+request.phone());
            });
        RegisterRestaurant entity = mapper.toEntity(request);
        RegisterRestaurant saved = restaurantRepository.save(entity);

        log.info("Restaurant created with id : {}",saved.getId());

        eventPublisher.publishRestaurantRegistered(RestaurantRegisteredEvent.of(saved.getId(),saved.getName(),
                                                            saved.getPhone(),saved.getIsActive()));

        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantResponseDto> getAll() {
        log.info("Fetching all restaurants");
        return restaurantRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
