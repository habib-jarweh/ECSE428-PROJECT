package ecse428.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ecse428.backend.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
    
}
