package dev.bispro.services.impl;

import dev.bispro.domain.Restaurant;
import dev.bispro.persistence.RestaurantRepository;
import dev.bispro.services.RestaurantService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant findByRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ServiceLayerException("Restaurant not found with ID: " + restaurantId));
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        if (restaurant == null) {
            throw new ServiceLayerException("Restaurant cannot be null");
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(restaurantId);
        if (existingRestaurant.isPresent()) {
            Restaurant updatedRestaurant = existingRestaurant.get();
            updatedRestaurant.setName(restaurant.getName());
            updatedRestaurant.setStreet(restaurant.getStreet());
            updatedRestaurant.setNumber(restaurant.getNumber());
            updatedRestaurant.setCity(restaurant.getCity());
            updatedRestaurant.setPostalCode(restaurant.getPostalCode());
            updatedRestaurant.setState(restaurant.getState());
            updatedRestaurant.setEmployees(restaurant.getEmployees());
            updatedRestaurant.setOrders(restaurant.getOrders());
            updatedRestaurant.setLayers(restaurant.getLayers());
            return restaurantRepository.save(updatedRestaurant);
        } else {
            throw new ServiceLayerException("Restaurant not found with ID: " + restaurantId);
        }
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new ServiceLayerException("Cannot delete: Restaurant with ID " + restaurantId + " does not exist.");
        }
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
