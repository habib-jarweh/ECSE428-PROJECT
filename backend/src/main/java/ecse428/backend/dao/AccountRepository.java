package ecse428.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ecse428.backend.model.Account;

public interface AccountRepository extends CrudRepository<Account,String> {
    
}
