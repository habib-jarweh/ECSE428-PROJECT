package ecse428.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ecse428.backend.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, String> {
    
}
