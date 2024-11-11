package dev.bispro.services;

import dev.bispro.dtos.RestaurantDTO;

import java.util.List;

public interface RestaurantService {

    RestaurantDTO findByRestaurantById(Long restaurantId);
    RestaurantDTO updateRestaurant(Long restaurantId, RestaurantDTO restaurantDTO);
    void deleteRestaurant(Long restaurantId);
    List<RestaurantDTO> getAllRestaurants();
}
