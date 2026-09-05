package hameed.restaurant.event;


import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantRegisteredEvent(

        String eventId,
        String eventType,
        LocalDateTime occurredAt,

        Integer restaurantId,
        String name,
        String phone,
        Boolean isActive

) {
    public static RestaurantRegisteredEvent of(Integer restaurantId, String name,
                                               String phone, Boolean isActive){

        return new RestaurantRegisteredEvent(
                UUID.randomUUID().toString(),
        "RESTAURANT_REGISTERED",
                LocalDateTime.now(),
                restaurantId,name, phone, isActive
        );
    }
}
