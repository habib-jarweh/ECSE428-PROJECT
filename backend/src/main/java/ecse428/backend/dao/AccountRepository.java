package ecse428.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ecse428.backend.model.Account;

public interface AccountRepository extends JpaRepository<Account,String> {

    Account findByEmail(String email);
}
