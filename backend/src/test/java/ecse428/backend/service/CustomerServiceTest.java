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
        // Mocking behavior
        Customer customer = new Customer("test@example.com");
        when(customerRepository.findCustomerByEmail("test@example.com")).thenReturn(customer);

        // Create a set of dietary restrictions
        Set<DietaryRestriction> dietaryRestrictions = EnumSet.of(DietaryRestriction.Dairy, DietaryRestriction.Gluten);

        // Call the method under test
        assertDoesNotThrow(() -> customerService.setDietaryRestriction("test@example.com", dietaryRestrictions));

        // Verify the result
        assertEquals(dietaryRestrictions, customer.getDietaryRestrictions());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testSetDietaryRestriction_CustomerNotFound() {
        // Mocking behavior
        when(customerRepository.findCustomerByEmail("nonexistent@example.com")).thenReturn(null);

        // Create a set of dietary restrictions
        Set<DietaryRestriction> dietaryRestrictions = EnumSet.of(DietaryRestriction.Gluten, DietaryRestriction.Dairy);

        // Call the method under test and expect an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.setDietaryRestriction("nonexistent@example.com", dietaryRestrictions));
        assertEquals("Could not find customer with email address nonexistent@example.com.", exception.getMessage());

        // Verify interactions with dependencies
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    public void testupdateDietaryRestriction_DuplicateRestriction() {
        // Mocking behavior
        Customer customer = new Customer("test@example.com");
        customer.setDietaryRestrictions(EnumSet.of(DietaryRestriction.Halal));
        when(customerRepository.findCustomerByEmail("test@example.com")).thenReturn(customer);

        // Create a set of dietary restrictions with a duplicate restriction
        Set<DietaryRestriction> dietaryRestrictions = EnumSet.of(DietaryRestriction.Halal, DietaryRestriction.Vegan);

        // Call the method under test and expect an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.updateDietaryRestriction("test@example.com", dietaryRestrictions));
        assertEquals("Duplicate dietary restriction detected: Halal", exception.getMessage());

        // Verify interactions with dependencies
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    public void testUpdateDietaryRestriction_ValidNonDuplicate() {
        // Mocking behavior
        Customer customer = new Customer("valid@example.com");
        customer.setDietaryRestrictions(EnumSet.of(DietaryRestriction.Dairy));
        when(customerRepository.findCustomerByEmail("valid@example.com")).thenReturn(customer);

        // Create a set of non-duplicate dietary restrictions
        Set<DietaryRestriction> dietaryRestrictions = EnumSet.of(DietaryRestriction.Gluten, DietaryRestriction.Vegan);

        // Call the method under test
        customerService.updateDietaryRestriction("valid@example.com", dietaryRestrictions);

        // Verify the dietary restrictions were updated
        assertEquals(dietaryRestrictions, customer.getDietaryRestrictions());

        // Verify interactions with dependencies
        verify(customerRepository).save(customer);
    }

    @Test
    public void testValidateDietaryRestriction_NullSet() {
        // Expect an exception due to null dietary restrictions set
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> customerService.validateDietaryRestriction(null));
        assertEquals("Dietary restrictions cannot be null.", exception.getMessage());
    }

    @Test
    public void testValidateDietaryRestriction_AllValid() {
        // Assuming all specified restrictions are valid
        Set<DietaryRestriction> dietaryRestrictions = EnumSet.of(DietaryRestriction.Vegan, DietaryRestriction.Gluten);

        // Call the method under test
        boolean result = customerService.validateDietaryRestriction(dietaryRestrictions);

        // Verify the result is true for all valid restrictions
        assertTrue(result);
    }

}