package ecse428.backend.service;

import ecse428.backend.dao.CustomerRepository;
import ecse428.backend.dto.CustomerDto;
import ecse428.backend.model.Customer;
import ecse428.backend.model.SmartEats.DietaryRestriction;

import java.util.Set;

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
                Customer ex_customer = customerRepository.findCustomerByEmail(customerDto.getEmail());
                if (ex_customer != null) {
                    throw new IllegalArgumentException("A user with this email already exists.");
                }

                customerRepository.save(customer);
            } catch (DataIntegrityViolationException ex) {
                throw new IllegalArgumentException("A user with this email already exists.", ex);
            }
            return new CustomerDto(customer.getEmail(), null);
        }else{
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
    }

    public boolean checkCustomerCredentials(CustomerDto customerDto) {
        Customer customer = customerRepository.findCustomerByEmail(customerDto.getEmail());
        if (customer == null) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        if (!customer.getPassword().equals(customerDto.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        return true;
    }

    public CustomerDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("Could not find customer with email address " + email + ".");
        }
        return customer.convertToDto();
    }

    public void setDietaryRestriction(String email, Set<DietaryRestriction> dietaryRestrictions) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("Could not find customer with email address " + email + ".");
        }

        if (validateDietaryRestriction(dietaryRestrictions)) {

            for (DietaryRestriction restriction : dietaryRestrictions) {
                customer.setDietaryRestrictions(restriction);
            } 

            customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Invalid dietary restriction.");
        }
    }

    public void updateDietaryRestriction(String email, Set<DietaryRestriction> dietaryRestrictions) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("Could not find customer with email address " + email + ".");
        }

        if (validateDietaryRestriction(dietaryRestrictions)) {
            for (DietaryRestriction restriction : dietaryRestrictions) {
                if (!customer.getDietaryRestrictions().contains(restriction)) {
                    customer.setDietaryRestrictions(restriction);
                } else {
                    throw new IllegalArgumentException("Dietary restriction " + restriction + " already exists for the customer.");
                }
            }

            customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Invalid dietary restriction.");
        }
    }

    protected boolean validateDietaryRestriction(Set<DietaryRestriction> dietaryRestrictions) {

        for (DietaryRestriction restriction : DietaryRestriction.values()) {
            if (! dietaryRestrictions.contains(restriction)) {
                return false;
            }
        }
        return true;
    }

    protected boolean validatePassword(String password){
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        return true;
    }


}
