package az.edu.ada.wm2.Assignment2.mapper;

import az.edu.ada.wm2.Assignment2.model.dto.BookDto;
import az.edu.ada.wm2.Assignment2.model.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for mapping between DTOs (Data Transfer Objects) and entities.
 */
@Component
public class DtoEntityMapper {

    /**
     * Converting a BookDto object to a Book entity.
     *
     * @param bookDto The BookDto object to convert.
     * @return The corresponding Book entity.
     */
    public Book convertDtoToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        return book;
    }

    /**
     * Converting a Book entity to a BookDto object.
     *
     * @param book The Book entity to convert.
     * @return The corresponding BookDto object.
     */
    public BookDto convertEntityToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setDescription(book.getDescription());
        return bookDto;
    }

    /**
     * Converting a list of Book entities to a list of BookDto objects.
     *
     * @param books The list of Book entities to convert.
     * @return The corresponding list of BookDto objects.
     */
    public List<BookDto> convertEntityListToDtoList(List<Book> books) {
        return books.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
