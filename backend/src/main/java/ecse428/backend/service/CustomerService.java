package ecse428.backend.service;

import ecse428.backend.dao.CustomerRepository;
import ecse428.backend.dto.CustomerDto;
import ecse428.backend.model.Customer;
import ecse428.backend.model.SmartEats;
import ecse428.backend.model.SmartEats.Pair;
import java.time.LocalDate;
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
        Customer ex_customer = customerRepository.findCustomerByEmail(
          customerDto.getEmail()
        );
        if (ex_customer != null) {
          throw new IllegalArgumentException(
            "A user with this email already exists."
          );
        }

        customerRepository.save(customer);
      } catch (DataIntegrityViolationException ex) {
        throw new IllegalArgumentException(
          "A user with this email already exists.",
          ex
        );
      }
      return new CustomerDto(customer.getEmail(), null);
    } else {
      throw new IllegalArgumentException(
        "Password must be at least 8 characters long."
      );
    }
  }

  public boolean checkCustomerCredentials(CustomerDto customerDto) {
    Customer customer = customerRepository.findCustomerByEmail(
      customerDto.getEmail()
    );
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
      throw new IllegalArgumentException(
        "Could not find customer with email address " + email + "."
      );
    }
    
    return customer.convertToDto();
  }

  protected boolean validatePassword(String password) {
    
    if (password == null || password.isEmpty()) {
      throw new IllegalArgumentException("Password cannot be empty.");
    }
    if (password.length() < 8) {
      throw new IllegalArgumentException(
        "Password must be at least 8 characters long."
      );
    }
    return true;
  }

  public CustomerDto addUpdateWeightHistory(CustomerDto customerDto,Double weight) {
    
    Customer customer = customerRepository.findCustomerByEmail(customerDto.getEmail());

    if (customer == null) {
      throw new IllegalArgumentException("Could not find customer with email address " + customerDto.getEmail() + ".");
    }

    Set<SmartEats.Pair<LocalDate, Double>> weightHistory = customer.getWeightHistory();

    for (Pair<LocalDate, Double> pair : weightHistory) {
      if (pair.getFirst().equals(LocalDate.now())) {
        pair.setSecond(weight);

        customerRepository.save(customer);
        return customer.convertToDto();
      }
    }

    weightHistory.add(new Pair<LocalDate, Double>(LocalDate.now(), weight));
    customerRepository.save(customer);
    return customer.convertToDto();
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

}
