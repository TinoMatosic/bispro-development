package dev.bispro.persistence;

import dev.bispro.domain.Restaurant;
import dev.bispro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Restaurant.RestaurantId> {


}
