package ecse428.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecse428.backend.dto.CustomerDto;
import ecse428.backend.model.Customer;
import ecse428.backend.model.SmartEats.DietaryRestriction;
import ecse428.backend.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void registerUser_ValidUserDto_ReturnsOk() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto("test@example.com", "password");
        when(customerService.registerCustomer(customerDto)).thenReturn(customerDto);

        // Act & Assert
        mockMvc.perform(post("/customers/register")
                        .content(objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).registerCustomer(any(CustomerDto.class));
    }

    @Test
    void registerUser_InvalidUserDto_ReturnsBadRequest() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto("", "");
        BindingResult result = new BeanPropertyBindingResult(customerDto, "customerDto");

        // Act & Assert
        mockMvc.perform(post("/customers/register")
                        .content(objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(customerService, times(0)).registerCustomer(any(CustomerDto.class));
    }

    @Test
    void checkUserCredentials_ValidUserDto_ReturnsOk() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto("test@example.com", "password");
        when(customerService.checkCustomerCredentials(customerDto)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/customers/login")
                        .content(objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).checkCustomerCredentials(any(CustomerDto.class));
    }

    @Test
    void checkUserCredentials_InvalidUserDto_ReturnsBadRequest() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto("", "");
        BindingResult result = new BeanPropertyBindingResult(customerDto, "customerDto");

        // Act & Assert
        mockMvc.perform(post("/customers/login")
                        .content(objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(customerService, times(0)).checkCustomerCredentials(any(CustomerDto.class));
    }

        @Test
    void updateDietaryRestrictions_ValidDto_ReturnsOk() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail("test@example.com");
        customerDto.setDietaryRestrictions(new HashSet<>(Arrays.asList(DietaryRestriction.GLUTEN, DietaryRestriction.VEGAN)));

        when(customerService.setDietaryRestriction(eq("test@example.com"), any(String[].class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/customers/dietary-restrictions")
                        .content(objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).setDietaryRestriction(eq("test@example.com"), any(String[].class));
    }

    @Test
    void updateDietaryRestrictions_ServiceError_ReturnsInternalServerError() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail("test@example.com");
        customerDto.setDietaryRestrictions(new HashSet<>(Arrays.asList(DietaryRestriction.GLUTEN, DietaryRestriction.VEGAN)));

        when(customerService.setDietaryRestriction(eq("test@example.com"), any(String[].class))).thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/customers/dietary-restrictions")
                        .content(objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(customerService, times(1)).setDietaryRestriction(eq("test@example.com"), any(String[].class));
    }

    @Test
    void updateDietaryRestrictions_InvalidDtoFormat_ReturnsBadRequest() throws Exception {
        // Arrange
        String invalidDtoJson = "{\"email\": \"test@example.com\", \"dietaryRestrictions\": \"invalid\"}";

        // Act & Assert
        mockMvc.perform(post("/customers/dietary-restrictions")
                        .content(invalidDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(customerService, times(0)).setDietaryRestriction(anyString(), any(String[].class));
    }

    @Test
    void updateDietaryRestrictions_InvalidDtoFields_ReturnsBadRequest() throws Exception {
        // Arrange
        String invalidDtoJson = "{\"email\": \"\", \"dietaryRestrictions\": []}";

        // Act & Assert
        mockMvc.perform(post("/customers/dietary-restrictions")
                        .content(invalidDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(customerService, times(0)).setDietaryRestriction(anyString(), any(String[].class));
    }

    @Test
    void updateWeightGoal_ValidRequest_ReturnsOk() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto("test@example.com", "password");
        double newWeightGoal = 65.0;

        when(customerService.addUpdateWeightGoal(any(CustomerDto.class), eq(newWeightGoal))).thenReturn(customerDto);

        // Act & Assert
        mockMvc.perform(post("/customers/weightGoal")
                        .param("weightGoal", String.valueOf(newWeightGoal))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk());

        verify(customerService, times(1)).addUpdateWeightGoal(any(CustomerDto.class), eq(newWeightGoal));
    }
    @Test
    void updateWeightGoal_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto("test@example.com", "password");
        // Invalid weight goal (not provided in this case)

        // Act & Assert
        mockMvc.perform(post("/customers/weightGoal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isBadRequest());

        // Verify that the service method is never called due to invalid request
        verify(customerService, times(0)).addUpdateWeightGoal(any(CustomerDto.class), anyDouble());
    }


    @Test
    void updateUserWeightHistory_ValidUser_ReturnsOk() throws Exception {
                // Arrange
                CustomerDto customerDto = new CustomerDto("test@example.com", "password");

                // Act & Assert
                mockMvc.perform(post("/customers/weightHistory")
                                .content(objectMapper.writeValueAsString(customerDto))
                                .param("weight", "101.1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

                verify(customerService, times(1)).addUpdateWeightHistory(any(CustomerDto.class), any(Double.class));
    }

    @Test
    void updateUserWeightHistory_InvalidUser_ReturnsBadRequest() throws Exception {

                // Act & Assert
                mockMvc.perform(post("/customers/weightHistory")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());

                verify(customerService, times(0)).checkCustomerCredentials(any(CustomerDto.class));
    }

    @Test
    void get_all_customers() throws Exception {
        // Arrange
        List<CustomerDto> customers = Arrays.asList(new CustomerDto("test@me.com", "password"));
        when(customerService.getAllCustomers()).thenReturn(customers);

        // Act & Assert
        mockMvc.perform(get("/customers/view_all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"email\":\"test@me.com\", \"password\":\"password\"}]"));

        verify(customerService, times(1)).getAllCustomers();

    }



}
