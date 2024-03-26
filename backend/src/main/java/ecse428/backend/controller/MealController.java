package ecse428.backend.controller;

import ecse428.backend.dto.MealDto;
import ecse428.backend.model.Meal;
import ecse428.backend.model.SmartEats;
import ecse428.backend.service.MealService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    private boolean isAdminRequest(HttpServletRequest request) {
        // Example check: a specific header value
        String adminToken = request.getHeader("Admin-Token");
        return "RHPSOoCuK6QJBalhq4Cbv9yjhViKwXbXpioheADyw246gURS8GLcHsbPWb3cJBOs".equals(adminToken);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createMeal(@Valid @RequestBody MealDto mealDto, BindingResult result, HttpServletRequest request) {

        if (!isAdminRequest(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.ok(
                mealService.createMeal(mealDto)); 
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMeal(@Valid @RequestBody MealDto mealDto, BindingResult result,  HttpServletRequest request) {

        if (!isAdminRequest(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.ok(mealService.updateMeal(mealDto));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/view_all")
    public ResponseEntity<?> viewAllMeals() {
        try {
            return ResponseEntity.ok(mealService.getAllMeals());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/view_filter_by_dietary_restrictions")
    public ResponseEntity<?> viewMealsByDietaryRestrictions(@RequestBody List<SmartEats.DietaryRestriction> dietaryRestrictions) {
        try {
            return ResponseEntity.ok(mealService.getMealsByDietaryRestrictions(dietaryRestrictions));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}

