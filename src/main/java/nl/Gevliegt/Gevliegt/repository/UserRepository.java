package nl.Gevliegt.Gevliegt.repository;

import nl.Gevliegt.Gevliegt.model.Role;
import nl.Gevliegt.Gevliegt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}