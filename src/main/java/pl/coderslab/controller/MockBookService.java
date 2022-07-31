package pl.coderslab.controller;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Book;
import pl.coderslab.model.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@ToString
@Component
public class MockBookService implements BookService {
    private List<Book> list;

    @Autowired
    public MockBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        list.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    private static Long nextId = 4L;

    @Override
    public List<Book> getBooks() {
        return list;
    }

    @Override
    public Book getBookById(long id) {
        Optional<Book> first = list.stream()
                .filter(element -> element.getId() == id).findFirst();
        return first.get();
    }

    @Override
    public boolean addBook(Book book) {
        book.setId(nextId++);
        return list.add(book);
    }

    @Override
    public List<Book> updateBook(Book newBook) {
        Book oldBook = list.stream().filter(element -> element.getId().equals(newBook.getId())).findFirst().get();
        int index = list.indexOf(oldBook);
        list.set(index, newBook);
        return list;
    }

    @Override
    public boolean deleteBook(long id) {
        return list.removeIf(element -> element.getId() == id);
    }
}
