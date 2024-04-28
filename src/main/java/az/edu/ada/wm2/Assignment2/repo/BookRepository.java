package az.edu.ada.wm2.Assignment2.repo;

import az.edu.ada.wm2.Assignment2.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
