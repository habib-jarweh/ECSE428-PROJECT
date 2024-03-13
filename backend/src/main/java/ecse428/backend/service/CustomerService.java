package ecse428.backend.service;

import ecse428.backend.dao.CustomerRepository;
import ecse428.backend.dto.CustomerDto;
import ecse428.backend.model.Customer;
import ecse428.backend.model.WeightDate;
import ecse428.backend.model.SmartEats.DietaryRestriction;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public boolean setDietaryRestriction(String email, String[] dietaryRestrictions) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("Could not find customer with email address " + email + ".");
        }

        Set<DietaryRestriction> newRestrictions = convertDietaryRestriction(dietaryRestrictions);

        customer.setDietaryRestrictions(newRestrictions);
        customerRepository.save(customer);

        return true;

    }

    public String[] getDietaryRestriction(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("Could not find customer with email address " + email + ".");
        }

        Set<DietaryRestriction> newRestrictions = customer.getDietaryRestrictions();

        String[] restrictions = new String[newRestrictions.size()];
        int i = 0;
        for (DietaryRestriction restriction : newRestrictions) {
            restrictions[i] = restriction.toString();
            i++;
        }

        return restrictions;

    }




    protected Set<DietaryRestriction> convertDietaryRestriction(String[] dietaryRestriction) {
        if (dietaryRestriction == null) {
            throw new IllegalArgumentException("Dietary restriction cannot be null.");
        }
        
        Set<String> uniqueRestrictions = new HashSet<>();
        Set<DietaryRestriction> set = new HashSet<>();
        
        for (String restriction : dietaryRestriction) {
            String upperCaseRestriction = restriction.toUpperCase();
            
            // Check for duplicates
            if (!uniqueRestrictions.add(upperCaseRestriction)) {
                throw new IllegalArgumentException("Duplicate dietary restriction detected: " + restriction);
            }
            
            try {
                set.add(DietaryRestriction.valueOf(upperCaseRestriction));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(restriction + " is not a valid dietary restriction.");
            }
        }
        return set;
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
    public CustomerDto addUpdateWeightGoal(CustomerDto customerDto, Double weightGoal) {
    
        Customer customer = customerRepository.findCustomerByEmail(customerDto.getEmail());
    
        if (customer == null) {
            throw new IllegalArgumentException("Could not find customer with email address " + customerDto.getEmail() + ".");
        }
    
        if (weightGoal <= 0) {
            throw new IllegalArgumentException("Weight goal must be greater than 0.");
        }
    
        // Update the customer's weight goal
          customer.setWeightGoal(weightGoal);
    
        // Save the updated customer
        customerRepository.save(customer);
    
        // Return the updated customer information, excluding sensitive details like password
       return customer.convertToDto(); // Make sure the convertToDto method does not include the password
      }
      
    public CustomerDto setWeightGoal(CustomerDto customerDto, Double weightGoal) {
        Customer customer = customerRepository.findCustomerByEmail(customerDto.getEmail());
    
        if (customer == null) {
            throw new IllegalArgumentException("Could not find customer with email address " + customerDto.getEmail() + ".");
        }
    
        customer.setWeightGoal(weightGoal);
        customerRepository.save(customer);
        return customer.convertToDto();
    }

    public double getWeightGoal(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("Could not find customer with email address " + email + ".");
        }

        double weightGoal = customer.getWeightGoal();
        return weightGoal;

    }
    public CustomerDto addUpdateWeightHistory(CustomerDto customerDto,Double weight) {

        Customer customer = customerRepository.findCustomerByEmail(customerDto.getEmail());

        if (customer == null) {
        throw new IllegalArgumentException("Could not find customer with email address " + customerDto.getEmail() + ".");
        }

        Set<WeightDate> weightHistory = customer.getWeightHistory();

        for (WeightDate pair : weightHistory) {
        if (pair.getDate().equals(LocalDate.now())) {
            pair.setWeight(weight);

            customerRepository.save(customer);
            return customer.convertToDto();
        }
        }

        weightHistory.add(new WeightDate(LocalDate.now(), weight));
        customerRepository.save(customer);
        return customer.convertToDto();
    }

    public List<CustomerDto> getAllCustomers() {

        //Return null if no customers
        if(customerRepository.findAll() == null || customerRepository.findAll().isEmpty()){
            return null;
        }

        return customerRepository.findAll().stream().map(Customer::convertToDto).collect(Collectors.toList());
    }
    
    

}
