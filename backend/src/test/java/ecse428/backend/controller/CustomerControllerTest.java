package ecse428.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecse428.backend.dto.CustomerDto;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    void updateUserWeightHistory_ValidUser_ReturnsOk() throws Exception {
                // Arrange
                CustomerDto customerDto = new CustomerDto("test@example.com", "password");

                // Act & Assert
                mockMvc.perform(get("/customers/weightHistory")
                                .content(objectMapper.writeValueAsString(customerDto))
                                .param("weight", "101.1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
        
                verify(customerService, times(1)).addUpdateWeightHistory(any(CustomerDto.class), any(Double.class));
    }

    @Test
    void updateUserWeightHistory_InvalidUser_ReturnsBadRequest() throws Exception {

                // Act & Assert
                mockMvc.perform(get("/customers/weightHistory")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());
        
                verify(customerService, times(0)).checkCustomerCredentials(any(CustomerDto.class));
    }
}
