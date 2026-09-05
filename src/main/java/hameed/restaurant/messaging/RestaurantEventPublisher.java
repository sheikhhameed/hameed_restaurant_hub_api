package hameed.restaurant.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hameed.restaurant.event.RestaurantRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RestaurantEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;


    @Value("${hameed.kafka.topic.restaurant-events}")
    private String restaurantEventsTopic;


    public void publishRestaurantRegistered(RestaurantRegisteredEvent event) {

        publish(event.restaurantId(),event, "RESTAURANT_REGISTERED");
    }

    private void publish(Integer key, RestaurantRegisteredEvent event, String eventType) {

        try {
            String json = objectMapper.writeValueAsString(event);
             kafkaTemplate.send(restaurantEventsTopic, String.valueOf(key),json)
                     .whenComplete((result,ex) ->{
                        if(ex!=null){
                            log.error("Failed to publish {} for restaurantId ={} to topic {}: {}  "
                            , eventType, key, restaurantEventsTopic,ex.getMessage());


                        }else{
                            log.info("published {} for restaurant id: {}, to {}, [partition: {} , offset {} ",
                                    eventType, key, restaurantEventsTopic,result.getRecordMetadata().partition(),
                                    result.getRecordMetadata().offset());


                        }
                     });

        } catch (JsonProcessingException e) {
            log.error("could not serialize/published {} event {}", eventType, event);
        }

    }
}
