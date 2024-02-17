package ecse428.backend.service;

import ecse428.backend.dao.CustomerRepository;
import ecse428.backend.dto.CustomerDto;
import ecse428.backend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto registerCustomer(CustomerDto customerDto) {
        if (validatePassword(customerDto.getPassword())) {

            Customer customer = customerDto.convertToEntity();

            try {
                customerRepository.save(customer);
            } catch (DataIntegrityViolationException ex) {
                throw new IllegalArgumentException("A user with this email already exists.", ex);
            }
            return new CustomerDto(customer.getEmail(), null);
        }else{
            throw new IllegalArgumentException("Password must be at least 8 characters long, contain at least one digit, one uppercase letter, one lowercase letter, and one special character.");
        }
    }

    public boolean checkCustomerCredentials(CustomerDto customerDto) {
        Customer customer = customerRepository.findByEmail(customerDto.getEmail());
        if (customer == null) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        if (!customer.getPassword().equals(customerDto.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        return true;
    }

    public CustomerDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customerRepository == null) {
            throw new IllegalArgumentException("Could not find customer with email address " + email + ".");
        }
        return customer.convertToDto();
    }

    private boolean validatePassword(String password){
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        return true;
    }

}
