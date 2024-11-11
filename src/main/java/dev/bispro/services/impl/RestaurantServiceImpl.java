package dev.bispro.services.impl;

import dev.bispro.domain.Restaurant;
import dev.bispro.dtos.RestaurantDTO;
import dev.bispro.persistence.RestaurantRepository;
import dev.bispro.services.RestaurantService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantDTO findByRestaurantById(Long restaurantId) {
        validateRestaurantId(restaurantId);
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> ServiceLayerException.notFound("Restaurant not found with ID: " + restaurantId));
        return toRestaurantDTO(restaurant);
    }
/**
    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        if (restaurantDTO == null) {
            throw ServiceLayerException.forInvalidArgument("Restaurant cannot be null");
        }
        validateRestaurantData(restaurantDTO);

        Restaurant restaurant = new Restaurant();
        updateRestaurantFields(restaurant, restaurantDTO);
        try {
            return toRestaurantDTO(restaurantRepository.save(restaurant));
        } catch (Exception e) {
            throw ServiceLayerException.forCreateError("Error creating Restaurant: " + e.getMessage());
        }
    }
**/
    @Override
    public RestaurantDTO updateRestaurant(Long restaurantId, RestaurantDTO restaurantDTO) {
        validateRestaurantId(restaurantId);
        validateRestaurantData(restaurantDTO);

        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> ServiceLayerException.notFound("Restaurant not found with ID: " + restaurantId));

        updateRestaurantFields(existingRestaurant, restaurantDTO);
        try {
            return toRestaurantDTO(restaurantRepository.save(existingRestaurant));
        } catch (Exception e) {
            throw ServiceLayerException.forUpdateError("Error updating Restaurant: " + e.getMessage());
        }
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        validateRestaurantId(restaurantId);
        try {
            restaurantRepository.deleteById(restaurantId);
        } catch (Exception e) {
            throw ServiceLayerException.forDeleteError("Error deleting Restaurant with Id [" + restaurantId + "]: " + e.getMessage());
        }
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        try {
            return restaurantRepository.findAll().stream()
                    .map(this::toRestaurantDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw ServiceLayerException.forGetError("Error retrieving all Restaurants: " + e.getMessage());
        }
    }


    private RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getStreet(),
                restaurant.getNumber(),
                restaurant.getCity(),
                restaurant.getPostalCode(),
                restaurant.getState()

        );
    }


    private void validateRestaurantId(Long restaurantId) {
        if (restaurantId == null || restaurantId < 0) {
            throw ServiceLayerException.forInvalidArgument("Invalid restaurant ID: " + restaurantId);
        }
    }


    private void validateRestaurantData(RestaurantDTO restaurantDTO) {
        if (restaurantDTO.getName() == null || restaurantDTO.getName().isEmpty() ||
                restaurantDTO.getStreet() == null || restaurantDTO.getCity() == null ||
                restaurantDTO.getPostalCode() == null || restaurantDTO.getState() == null ||
                restaurantDTO.getNumber() == null) {
            throw ServiceLayerException.forInvalidArgument("Restaurant data contains null or empty fields");
        }
    }


    private void updateRestaurantFields(Restaurant restaurant, RestaurantDTO restaurantDTO) {
        restaurant.setName(restaurantDTO.getName());
        restaurant.setStreet(restaurantDTO.getStreet());
        restaurant.setNumber(restaurantDTO.getNumber());
        restaurant.setCity(restaurantDTO.getCity());
        restaurant.setPostalCode(restaurantDTO.getPostalCode());
        restaurant.setState(restaurantDTO.getState());

    }
}
