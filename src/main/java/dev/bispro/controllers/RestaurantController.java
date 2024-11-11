// dev.bispro.controllers.RestaurantController.java
package dev.bispro.controllers;

import dev.bispro.domain.Restaurant;
import dev.bispro.dtos.RestaurantDTO;
import dev.bispro.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.findByRestaurantById(restaurantId));
    }
/**
    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurant) {
        return ResponseEntity.ok(restaurantService.createRestaurant(restaurant));
    }
**/
    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantDTO restaurant) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId, restaurant));
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }
}
