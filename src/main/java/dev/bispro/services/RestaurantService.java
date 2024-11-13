package dev.bispro.services;

import dev.bispro.domain.Restaurant;
import dev.bispro.dtos.RestaurantDTO;

import java.util.List;

public interface RestaurantService {

    RestaurantDTO findByRestaurantById(Restaurant.RestaurantId restaurantId);
    RestaurantDTO updateRestaurant(Restaurant.RestaurantId restaurantId, RestaurantDTO restaurantDTO);
    void deleteRestaurant(Restaurant.RestaurantId restaurantId);
    List<RestaurantDTO> getAllRestaurants();
}
