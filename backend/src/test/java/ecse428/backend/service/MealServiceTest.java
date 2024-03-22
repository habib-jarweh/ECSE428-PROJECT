package ecse428.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ecse428.backend.dao.MealRepository;
import ecse428.backend.dto.MealDto;
import ecse428.backend.model.Meal;

public class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateMeal_Success() {
        MealDto mealDto = new MealDto(); // Assuming a constructor or setters to set properties
        Meal meal = new Meal(); // Same here
        
        when(mealRepository.findMealByMealName(anyString())).thenReturn(null);
        when(mealRepository.save(any(Meal.class))).thenReturn(meal);

        Meal createdMeal = mealService.createMeal(mealDto);
        
        assertNotNull(createdMeal);
        verify(mealRepository).save(any(Meal.class));
    }

    @Test
    public void updateMeal_Success() {

        String mealName = "TestMeal";
        Meal meal = new Meal();
        meal.setMealName(mealName);
        meal.setDescription("Original Description");
        meal.setRating(5.0);
        meal.setPrice(20.0);
        meal.setImageLink("Original ImageLink");
        meal.setStockQuantity(10);

        MealDto mealDto = new MealDto();
        mealDto.setMealName(mealName);
        mealDto.setDescription("Updated Description");
        mealDto.setRating(4.5);
        mealDto.setPrice(25.0);
        mealDto.setImageLink("Updated ImageLink");
        mealDto.setStockQuantity(15);

        when(mealRepository.findMealByMealName(mealName)).thenReturn(meal);

        // Act
        Meal updatedMeal = mealService.updateMeal(mealDto);
        verify(mealRepository, times(1)).save(meal);
    }

    @Test
    void testGetAllMeals_Success() {
        when(mealRepository.findAll()).thenReturn(Collections.singletonList(new Meal()));

        List<MealDto> meals = mealService.getAllMeals();

        assertNotNull(meals);
        assertFalse(meals.isEmpty());
        assertEquals(1, meals.size());
    }
}
