package az.edu.ada.wm2.Assignment2.repo;

import az.edu.ada.wm2.Assignment2.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA repository interface for managing User entities.
 * Extends JpaRepository which provides basic CRUD operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by username.
     *
     * @param username The username to search for.
     * @return An Optional containing the user if found, empty otherwise.
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user exists with the given username.
     *
     * @param username The username to check.
     * @return True if a user exists with the given username, false otherwise.
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user exists with the given email address.
     *
     * @param email The email address to check.
     * @return True if a user exists with the given email address, false otherwise.
     */
    boolean existsByEmail(String email);
}
