package hameed.restaurant.repository;

import hameed.restaurant.model.RegisterRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<RegisterRestaurant, Integer> {


    Optional<RegisterRestaurant> findByPhone(String phone);
}
