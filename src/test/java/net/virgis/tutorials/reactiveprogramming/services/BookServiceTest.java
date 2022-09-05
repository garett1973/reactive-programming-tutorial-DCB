package net.virgis.tutorials.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookInfoService bookInfoService = new BookInfoService();
    private ReviewService reviewService = new ReviewService();
    private BookService bookService = new BookService(new BookInfoService(), new ReviewService());

    @Test
    void getBooks() {
        var books = bookService.getBooks();

        StepVerifier.create(books)
                .assertNext(book -> {
                    assertEquals("The Alchemist", book.getBookInfo().getTitle());
                    assertEquals(5, book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("The Kite Runner", book.getBookInfo().getTitle());
                    assertEquals(5, book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("The Da Vinci Code", book.getBookInfo().getTitle());
                    assertEquals(5, book.getReviews().size());
                })
                .verifyComplete();
    }

    @Test
    void getBookById() {
        var book = bookService.getBookById(1);

        StepVerifier.create(book)
                .assertNext(book1 -> {
                    assertEquals("The Alchemist", book1.getBookInfo().getTitle());
                    assertEquals(5, book1.getReviews().size());
                })
                .verifyComplete();
    }
}