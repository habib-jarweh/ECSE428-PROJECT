package ecse428.backend.service;


import ecse428.backend.dao.CustomerRepository;
import ecse428.backend.dto.CustomerDto;
import ecse428.backend.model.Customer;
import ecse428.backend.model.SmartEats.DietaryRestriction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;



public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterCustomer_Success() {
        // Mocking behavior
        when(customerRepository.findCustomerByEmail("test@example.com")).thenReturn(null);

        // Create a CustomerDto for registration
        CustomerDto customerDto = new CustomerDto("test@example.com", "password");

        // Call the method under test
        CustomerDto result = customerService.registerCustomer(customerDto);

        // Verify the result
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertNull(result.getName());

        // Verify interactions with dependencies
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testRegisterCustomer_DuplicateEmail() {
        // Mocking behavior
        when(customerRepository.findCustomerByEmail("test@example.com")).thenReturn(new Customer("test@example.com"));

        // Create a CustomerDto for registration
        CustomerDto customerDto = new CustomerDto("test@example.com", "password");

        // Call the method under test and expect an exception
        assertThrows(IllegalArgumentException.class, () -> customerService.registerCustomer(customerDto));

        // Verify interactions with dependencies
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    public void testCheckCustomerCredentials_Success() {
        // Mocking behavior
        Customer customer = new Customer("test@example.com");
        customer.setPassword("password");
        when(customerRepository.findCustomerByEmail("test@example.com")).thenReturn(customer);

        // Create a CustomerDto for credentials checking
        CustomerDto customerDto = new CustomerDto("test@example.com", "password");

        // Call the method under test
        boolean result = customerService.checkCustomerCredentials(customerDto);

        // Verify the result
        assertTrue(result);
    }

    @Test
    public void testCheckCustomerCredentials_InvalidEmail() {
        // Mocking behavior
        when(customerRepository.findCustomerByEmail("nonexistent@example.com")).thenReturn(null);

        // Create a CustomerDto for credentials checking
        CustomerDto customerDto = new CustomerDto("nonexistent@example.com", "password");

        // Call the method under test and expect an exception
        assertThrows(IllegalArgumentException.class, () -> customerService.checkCustomerCredentials(customerDto));
    }

    @Test
    public void testCheckCustomerCredentials_InvalidPassword() {
        // Mocking behavior
        Customer customer = new Customer("test@example.com");
        customer.setPassword("password");
        when(customerRepository.findCustomerByEmail("test@example.com")).thenReturn(customer);

        // Create a CustomerDto with wrong password for credentials checking
        CustomerDto customerDto = new CustomerDto("test@example.com", "wrongpassword");

        // Call the method under test and expect an exception
        assertThrows(IllegalArgumentException.class, () -> customerService.checkCustomerCredentials(customerDto));
    }

    @Test
    public void testGetCustomerByEmail_Success() {
        // Mocking behavior
        Customer customer = new Customer("test@example.com");
        when(customerRepository.findCustomerByEmail("test@example.com")).thenReturn(customer);

        // Call the method under test
        CustomerDto result = customerService.getCustomerByEmail("test@example.com");

        // Verify the result
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    public void testGetCustomerByEmail_NotFound() {
        // Mocking behavior
        when(customerRepository.findCustomerByEmail("nonexistent@example.com")).thenReturn(null);

        // Call the method under test and expect an exception
        assertThrows(IllegalArgumentException.class, () -> customerService.getCustomerByEmail("nonexistent@example.com"));
    }

    @Test
    public void testValidatePassword_Success() {
        // Call the method under test
        boolean result = customerService.validatePassword("password123");

        // Verify the result
        assertTrue(result);
    }

    @Test
    public void testValidatePassword_NullPassword() {
        // Call the method under test and expect an exception
        assertThrows(IllegalArgumentException.class, () -> customerService.validatePassword(null));
    }

    @Test
    public void testValidatePassword_EmptyPassword() {
        // Call the method under test and expect an exception
        assertThrows(IllegalArgumentException.class, () -> customerService.validatePassword(""));
    }

    @Test
    public void testValidatePassword_TooShortPassword() {
        // Call the method under test and expect an exception
        assertThrows(IllegalArgumentException.class, () -> customerService.validatePassword("short"));
    }

    @Test
    public void testSetDietaryRestriction_Success() {
        // Given
        String email = "test@example.com";
        Customer customer = new Customer(email);
        when(customerRepository.findCustomerByEmail(email)).thenReturn(customer);
        String[] dietaryRestrictions = {"DAIRY", "GLUTEN"};

        // When
        customerService.setDietaryRestriction(email, dietaryRestrictions);

        // Then
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testSetDietaryRestriction_CustomerNotFound() {
        // Given
        String email = "nonexistent@example.com";
        when(customerRepository.findCustomerByEmail(email)).thenReturn(null);
        String[] dietaryRestrictions = {"GLUTEN", "DAIRY"};

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.setDietaryRestriction(email, dietaryRestrictions));
        assertEquals("Could not find customer with email address " + email + ".", exception.getMessage());
    }

    @Test
    public void testSetDietaryRestriction_DuplicateRestrictions() {
        // Given
        String email = "duplicate@example.com";
        Customer customer = new Customer(email);
        when(customerRepository.findCustomerByEmail(email)).thenReturn(customer);
        String[] dietaryRestrictions = {"GLUTEN", "GLUTEN"};

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.setDietaryRestriction(email, dietaryRestrictions));
        assertEquals("Duplicate dietary restriction detected: GLUTEN", exception.getMessage());
    }

    @Test
    public void testSetDietaryRestriction_InvalidRestriction() {
        // Given
        String email = "invalid@example.com";
        Customer customer = new Customer(email);
        when(customerRepository.findCustomerByEmail(email)).thenReturn(customer);
        String[] dietaryRestrictions = {"DAIRY", "INVALID"};

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.setDietaryRestriction(email, dietaryRestrictions));
        assertEquals("INVALID is not a valid dietary restriction.", exception.getMessage());
    }

    @Test
    public void testSetDietaryRestriction_NullRestrictions() {
        // Given
        String email = "null@example.com";
        Customer customer = new Customer(email);
        when(customerRepository.findCustomerByEmail(email)).thenReturn(customer);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.setDietaryRestriction(email, null));
        assertEquals("Dietary restriction cannot be null.", exception.getMessage());
    }


}