package ecse428.backend.service;

import ecse428.backend.model.Meal;
import ecse428.backend.dao.MealRepository;
import ecse428.backend.dto.MealDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import ecse428.backend.model.SmartEats.Ingredient;

@Service
public class MealService {
    
    private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal createMeal(MealDto mealDto) {
        Meal meal = mealDto.convertToEntity();

        // Check if a meal with the same name already exists
        Meal existingMeal = mealRepository.findMealByMealName(meal.getMealName());
        if (existingMeal != null) {
            throw new IllegalArgumentException("A meal with this name already exists.");
        }

        try {
            // Attempt to save the new meal
            return mealRepository.save(meal);
        } catch (DataIntegrityViolationException ex) {
            // Handle possible data integrity issues, for example, if mealName must be unique.
            throw new IllegalArgumentException("A meal with this name already exists.", ex);
        }
    }

    public Meal updateMeal(MealDto mealDto) {
        Meal existingMeal = mealRepository.findMealByMealName(mealDto.getMealName());
        if (existingMeal == null) {
            throw new IllegalArgumentException("Meal not found.");
        }
    
        // Update the existing meal details
        existingMeal.setDescription(mealDto.getDescription());
        existingMeal.setRating(mealDto.getRating());
        existingMeal.setPrice(mealDto.getPrice());
        existingMeal.setIngredients(mealDto.getIngredients());
        existingMeal.setDietaryRestrictions(mealDto.getDietaryRestrictions());
        existingMeal.setImageLink(mealDto.getImageLink());
        existingMeal.setStockQuantity(mealDto.getStockQuantity());
    
        return mealRepository.save(existingMeal);
    }

        public List<MealDto> getAllMeals() {

        //Return null if no customers
        if(mealRepository.findAll() == null || mealRepository.findAll().isEmpty()){
            return null;
        }

        return mealRepository.findAll().stream().map(Meal::convertToDto).collect(Collectors.toList());
    }
    
}

