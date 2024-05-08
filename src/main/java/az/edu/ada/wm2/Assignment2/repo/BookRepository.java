package az.edu.ada.wm2.Assignment2.repo;

import az.edu.ada.wm2.Assignment2.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository interface for managing Book entities.
 * Extends JpaRepository which provides basic CRUD operations.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
