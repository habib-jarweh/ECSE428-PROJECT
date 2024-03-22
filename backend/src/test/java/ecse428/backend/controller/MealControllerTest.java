package ecse428.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecse428.backend.dto.CustomerDto;
import ecse428.backend.model.Customer;
import ecse428.backend.model.SmartEats.DietaryRestriction;
import ecse428.backend.service.CustomerService;
import ecse428.backend.service.MealService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import static org.mockito.BDDMockito.given;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ecse428.backend.dto.MealDto;
import ecse428.backend.model.Meal;

@ExtendWith(SpringExtension.class)
public class MealControllerTest {

    @InjectMocks
    private MealController mealController;

    @Mock
    private MealService mealService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mealController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void createMeal_Success() throws Exception {
        MealDto mealDto = new MealDto(); // Assume this is properly set up
        String mealDtoJson = "{\"mealName\":\"Test Meal\",\"description\":\"Delicious\",\"price\":15.99}"; // Simplified JSON representation

        given(mealService.createMeal(mealDto)).willReturn(new Meal()); 

        mockMvc.perform(post("/meals/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mealDtoJson)
                .header("Admin-Token", "RHPSOoCuK6QJBalhq4Cbv9yjhViKwXbXpioheADyw246gURS8GLcHsbPWb3cJBOs"))
            .andExpect(status().isOk());
    }

    @Test
    public void updateMeal_Success() throws Exception {
        String mealDtoJson = "{\"mealName\":\"Test Meal\",\"description\":\"Updated Description\",\"price\":16.99}";

        mockMvc.perform(MockMvcRequestBuilders.put("/meals/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mealDtoJson)
                .header("Admin-Token", "RHPSOoCuK6QJBalhq4Cbv9yjhViKwXbXpioheADyw246gURS8GLcHsbPWb3cJBOs"))
            .andExpect(status().isOk());
    }


    @Test
    public void viewAllMeals_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/meals/view_all")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    
}
