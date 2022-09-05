package net.virgis.tutorials.reactiveprogramming.services;

import net.virgis.tutorials.reactiveprogramming.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Review> getReviews(long bookId) {
        var reviewList = List.of(
                new Review(1, bookId, 4.5, "A great book"),
                new Review(2, bookId, 4.0, "A nice book"),
                new Review(3, bookId, 4.5, "A super book"),
                new Review(4, bookId, 4.0, "A good book"),
                new Review(5, bookId, 4.5, "A fantastic book")
        );

        return Flux.fromIterable(reviewList);
    }
}
