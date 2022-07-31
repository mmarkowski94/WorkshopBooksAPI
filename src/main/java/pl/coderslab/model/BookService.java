package pl.coderslab.model;

import java.util.List;

public interface BookService {

    List<Book> getBooks();

    Book getBookById(long id);

    boolean addBook(Book book);

    List<Book> updateBook(Book book);

    boolean deleteBook(long id);
}

