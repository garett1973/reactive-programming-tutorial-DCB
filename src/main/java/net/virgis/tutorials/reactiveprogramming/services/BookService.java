package net.virgis.tutorials.reactiveprogramming.services;


import lombok.extern.slf4j.Slf4j;
import net.virgis.tutorials.reactiveprogramming.domain.Book;
import net.virgis.tutorials.reactiveprogramming.domain.Review;
import net.virgis.tutorials.reactiveprogramming.exception.BookException;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BookService {

    private BookInfoService bookInfoService;
    private ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getBooks() {
        var allBooks = bookInfoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is: ", throwable);
                    return new BookException("Exception occurred while getting books");
                })
                .log();
    }

    public Flux<Book> getBooksRetry() {
        var allBooks = bookInfoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is: ", throwable);
                    return new BookException("Exception occurred while getting books");
                })
                .retry(3) // retry 3 times
                .log();
    }

    public Flux<Book> getBooksRetryWhen() {

        var allBooks = bookInfoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is: ", throwable);
                    return new BookException("Exception occurred while getting books");
                })
                .retryWhen(getRetryBackoffSpec()) // retry 3 times with 1 second delay between retries
                .log();
    }

    private static RetryBackoffSpec getRetryBackoffSpec() {
        return Retry.backoff(
                        3, // retry 3 times
                        Duration.ofMillis(1000) // wait 1 second between retries
                ).filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure())
                );
    }

    public Mono<Book> getBookById(long bookId) {
        var book = bookInfoService.getBookById(bookId);
        var review = reviewService.getReviews(bookId).collectList();

        return book
                .zipWith(review, (bookInfo, reviews) -> new Book(bookInfo, reviews))
                .log();
    }
}
