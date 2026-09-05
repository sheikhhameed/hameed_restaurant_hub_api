package hameed.restaurant.dto;

import java.time.LocalDateTime;

public record RestaurantResponseDto(Integer id,
                                    String name,
                                    String description,
                                    String address,
                                    String landmark,
                                    String phone,
                                    Double latitude,
                                    Double longitude,
                                    Double rating,
                                    Double minOrderPrice,
                                    Double deliveryCharges,
                                    Integer deliveryTime,
                                    Boolean isPureVeg,
                                    Boolean isActive,
                                    Boolean isFeatured,
                                    LocalDateTime createdAt,
                                    LocalDateTime updatedAt
) {}
