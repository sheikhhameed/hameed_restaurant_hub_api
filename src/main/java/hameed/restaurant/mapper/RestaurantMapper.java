package hameed.restaurant.mapper;

import hameed.restaurant.dto.RestaurantRequestDto;
import hameed.restaurant.dto.RestaurantResponseDto;
import hameed.restaurant.model.RegisterRestaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantResponseDto toDto(RegisterRestaurant entity){

        return new RestaurantResponseDto(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getAddress(),
                entity.getLandmark(),
                entity.getPhone(),
        entity.getLongitude(),
        entity.getLatitude(),
                entity.getRating(),
                entity.getMinOrderPrice(),
                entity.getDeliveryCharges(),
                entity.getDeliveryTime(),
                entity.getIsPureVeg(),
                entity.getIsActive(),
                entity.getIsFeatured(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public RegisterRestaurant toEntity(RestaurantRequestDto dto) {
        return RegisterRestaurant.builder()
                .name(dto.name())
                .description(dto.description())
                .address(dto.address())
                .landmark(dto.landmark())
                .pincode(dto.pincode())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .phone(dto.phone())
                .image(dto.image())
                .certificate(dto.certificate())
                .rating(dto.rating())
                .minOrderPrice(dto.minOrderPrice())
                .deliveryCharges(dto.deliveryCharges())
                .deliveryTime(dto.deliveryTime())
                .commissionRate(dto.commissionRate())
                .isPureVeg(dto.isPureVeg() != null && dto.isPureVeg())
                .isActive(dto.isActive() == null || dto.isActive())
                .isAccepted(dto.isAccepted() != null && dto.isAccepted())
                .isFeatured(dto.isFeatured() != null && dto.isFeatured())
                .autoAcceptable(dto.autoAcceptable() != null && dto.autoAcceptable())
                .build();
    }
}
