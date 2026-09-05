package hameed.restaurant.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestaurantRequestDto(
    @NotBlank(message = "Name is required")
    String name,
    String description,

    @NotBlank(message = "Address is required")
    String address,
    String landmark,
    String pincode,

    @NotNull(message = "latitude is required")
    Double latitude,

    @NotNull(message = "longitude is required")
    Double longitude,

    @NotBlank(message = "phone is required")
    String phone,

    String image,
    String certificate,
    @DecimalMin("0.0") @DecimalMax("5.0")
    Double rating,

    Double minOrderPrice,
    Double deliveryCharges,
    Integer deliveryTime,
    Double commissionRate,

    Boolean isPureVeg,
    Boolean isActive,
    Boolean isAccepted,
    Boolean isFeatured,
    Boolean autoAcceptable
    ){}
