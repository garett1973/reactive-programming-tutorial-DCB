package net.virgis.tutorials.reactiveprogramming.services;

import net.virgis.tutorials.reactiveprogramming.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {
    public Flux<BookInfo> getBooks() {
        var books = List.of(
                new BookInfo(1, "The Alchemist", "Paulo Coelho", "978-0062315003"),
                new BookInfo(2, "The Kite Runner", "Khaled Hosseini", "978-1594480006"),
                new BookInfo(3, "The Da Vinci Code", "Dan Brown", "978-0307474278")
        );

        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId) {
        var book = new BookInfo(bookId, "The Alchemist", "Paulo Coelho", "978-0062315003");
        return Mono.just(book);
    }
}
