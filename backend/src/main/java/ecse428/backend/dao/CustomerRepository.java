package ecse428.backend.dao;

import ecse428.backend.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    Customer findByEmail(String email);
}
