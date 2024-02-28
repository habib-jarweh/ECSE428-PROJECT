package ecse428.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecse428.backend.dto.CustomerDto;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.HashSet;

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
        mockMvc.perform(get("/customers/login")
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
        mockMvc.perform(get("/customers/login")
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

    // @Test
    // void updateWeightGoal_ValidRequest_ReturnsOk() throws Exception {
    //     // Arrange
    //     CustomerDto customerDto = new CustomerDto("test@example.com", "password");
    //     //Double weightGoal = 75.0; // Example weight goal
    //    // when(customerService.setWeightGoal(customerDto, weightGoal)).thenReturn(customerDto);

    //     // Act & Assert
    //     mockMvc.perform(post("/customers/setWeightGoal")
    //                     .content(objectMapper.writeValueAsString(customerDto))
    //                     .param("weightGoal", "75.0")
    //                     .contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isOk());

    //     verify(customerService, times(1)).setWeightGoal(any(CustomerDto.class), any(Double.class));
    // }

    // @Test
    // void updateWeightGoal_InvalidRequest_ReturnsBadRequest() throws Exception {
    //     //Arrange
    //     CustomerDto customerDto = new CustomerDto("", ""); // Invalid DTO
    //     Double weightGoal = null; // Missing weight goal

    //     //Act & Assert
    //     mockMvc.perform(post("/customers/setWeightGoal")
    //                     .content(objectMapper.writeValueAsString(customerDto))
    //                     .param("weightGoal", "") // Not setting the weight goal intentionally
    //                     .contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isBadRequest());



    //     verify(customerService, times(0)).setWeightGoal(any(CustomerDto.class), any(Double.class));
    // }
    // @Test
    // public void testAddUpdateWeightGoal_Success() {
    //     // Mocking behavior
    //     Customer customer = new Customer("test@example.com");
    //     when(customerRepository.findCustomerByEmail("test@example.com")).thenReturn(customer);

    //     CustomerDto customerDto = new CustomerDto("test@example.com", "whatever");

    //     // Assuming the initial weight goal is not set or different
    //     CustomerDto result = customerService.addUpdateWeightGoal(customerDto, 75.0);

    //     assertNotNull(result);
    //     assertEquals(75.0, result.getWeightGoal());

    //     verify(customerRepository, times(1)).save(any(Customer.class));
    // }

    // @Test
    // public void testAddUpdateWeightGoal_CustomerNotFound() {
    //     // Mocking behavior
    //     when(customerRepository.findCustomerByEmail("nonexistent@example.com")).thenReturn(null);

    //     CustomerDto customerDto = new CustomerDto("nonexistent@example.com", "whatever");

    //     // Expect an exception
    //     assertThrows(IllegalArgumentException.class, () -> customerService.addUpdateWeightGoal(customerDto, 75.0));
    // }

    // @Test
    // public void testAddUpdateWeightGoal_InvalidWeightGoal() {
    //     Customer customer = new Customer("test@example.com");
    //     when(customerRepository.findCustomerByEmail("test@example.com")).thenReturn(customer);

    //     CustomerDto customerDto = new CustomerDto("test@example.com", "whatever");

    //     // Expect an exception for invalid weight goal
    //     assertThrows(IllegalArgumentException.class, () -> customerService.addUpdateWeightGoal(customerDto, -10.0));
    // }

    @Test
    void setWeightGoal_ValidRequest_ReturnsOk() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto("test@example.com", "password");
        double weightGoal = 70.0;

        when(customerService.setWeightGoal(any(CustomerDto.class), eq(weightGoal))).thenReturn(customerDto);

        // Act & Assert
        mockMvc.perform(post("/customers/setWeightGoal")
                        .param("weightGoal", String.valueOf(weightGoal))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk());

        verify(customerService, times(1)).setWeightGoal(any(CustomerDto.class), eq(weightGoal));
    }
    @Test
    void setWeightGoal_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto("test@example.com", "password");
        // Invalid weight goal (not provided in this case)

        // Act & Assert
        mockMvc.perform(post("/customers/setWeightGoal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isBadRequest());

        // Verify that the service method is never called due to invalid request
        verify(customerService, times(0)).setWeightGoal(any(CustomerDto.class), anyDouble());
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


}
