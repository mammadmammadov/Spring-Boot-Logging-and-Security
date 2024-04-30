package az.edu.ada.wm2.Assignment2.mapper;

import az.edu.ada.wm2.Assignment2.model.dto.BookDto;
import az.edu.ada.wm2.Assignment2.model.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoEntityMapper {

    public Book convertDtoToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        return book;
    }

    public BookDto convertEntityToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setDescription(book.getDescription());
        return bookDto;
    }

    public List<BookDto> convertEntityListToDtoList(List<Book> books) {
        return books.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
