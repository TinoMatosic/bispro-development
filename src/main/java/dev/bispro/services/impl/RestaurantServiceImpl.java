package dev.bispro.services.impl;

import dev.bispro.domain.Restaurant;
import dev.bispro.domain.User;
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
                .orElseThrow(() -> ServiceLayerException.notFound("Restaurant not found with ID: " + restaurantId));
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        if (restaurant == null) {
            throw ServiceLayerException.forInvalidArgument("Restaurant cannot be null");
        }
        validateArguments(null, restaurant);
        try {
            return restaurantRepository.save(restaurant);
        } catch (Exception e) {
            throw ServiceLayerException.forCreateError("Error creating Restaurant: " + e.getMessage());
        }
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant) {
        validateArguments(restaurantId, restaurant);
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(restaurantId);
        if (existingRestaurant.isPresent()) {
            try {
                Restaurant updatedRestaurant = getRestaurant(restaurant, existingRestaurant);
                return restaurantRepository.save(updatedRestaurant);
            } catch (Exception e) {
                throw ServiceLayerException.forUpdateError("Error updating Restaurant: " + e.getMessage());
            }
        } else {
            throw ServiceLayerException.notFound("Restaurant not found with ID: " + restaurantId);
        }
    }

    private static Restaurant getRestaurant(Restaurant restaurant, Optional<Restaurant> existingRestaurant) {
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
        return updatedRestaurant;
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        validateArguments(restaurantId, null);
        try {
            restaurantRepository.deleteById(restaurantId);
        } catch (Exception e) {
            throw ServiceLayerException.forDeleteError("Error Deleting Restaurant with Id [" + restaurantId + "]: " + e.getMessage());
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        try {
            return restaurantRepository.findAll();
        } catch (Exception e) {
            throw ServiceLayerException.forGetError("Error retrieving all Restaurants: " + e.getMessage());
        }
    }

    private void validateArguments(Long restaurantId, Restaurant restaurant) {
        if (restaurantId != null && restaurantId < 0) {
            throw ServiceLayerException.forInvalidArgument("Invalid restaurant ID: " + restaurantId);
        }
        if (restaurant != null) {
            if (restaurant.getId() == null || restaurant.getName() == null ||
                    restaurant.getStreet() == null || restaurant.getCity() == null ||
                    restaurant.getPostalCode() == null || restaurant.getState() == null ||
                    restaurant.getEmployees() == null || restaurant.getLayers() == null ||
                    restaurant.getNumber() == null) {
                throw ServiceLayerException.forInvalidArgument("Restaurant object contains null fields");
            }
        }
    }
}
