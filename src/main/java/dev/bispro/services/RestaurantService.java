package dev.bispro.services;

import dev.bispro.domain.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant findByRestaurantById(Long restaurantId);

    Restaurant createRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant);

    void deleteRestaurant(Long restaurantId);

    List<Restaurant> getAllRestaurants();

}
