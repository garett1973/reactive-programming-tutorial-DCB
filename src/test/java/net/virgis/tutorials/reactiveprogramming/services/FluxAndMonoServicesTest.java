package net.virgis.tutorials.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxAndMonoServices.fruitsFlux();

        StepVerifier.create(fruitsFlux)
                .expectNext("Apple")
                .expectNext("Banana")
                .expectNext("Orange")
                .expectNext("Pear")
                .verifyComplete();
    }

    @Test
    void fruitMono() {
        var fruitMono = fluxAndMonoServices.fruitMono();

        StepVerifier.create(fruitMono)
                .expectNext("Mango")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {

        var fruitsFluxMap = fluxAndMonoServices.fruitsFluxMap();
        StepVerifier.create(fruitsFluxMap)
                .expectNext("APPLE")
                .expectNext("BANANA")
                .expectNext("ORANGE")
                .expectNext("PEAR")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitsFluxFilter = fluxAndMonoServices.fruitsFluxFilter(5);
        StepVerifier.create(fruitsFluxFilter)
                .expectNext("Banana")
                .expectNext("Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        var fruitsFluxFilterMap = fluxAndMonoServices.fruitsFluxFilterMap(5);
        StepVerifier.create(fruitsFluxFilterMap)
                .expectNext("BANANA")
                .expectNext("ORANGE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {
        var fruitsFluxFlatMap = fluxAndMonoServices.fruitsFluxFlatMap();
        StepVerifier.create(fruitsFluxFlatMap)
                .expectNext("A")
                .expectNext("p")
                .expectNext("p")
                .expectNext("l")
                .expectNext("e")
                .expectNext("B")
                .expectNext("a")
                .expectNext("n")
                .expectNext("a")
                .expectNext("n")
                .expectNext("a")
                .expectNext("O")
                .expectNext("r")
                .expectNext("a")
                .expectNext("n")
                .expectNext("g")
                .expectNext("e")
                .expectNext("P")
                .expectNext("e")
                .expectNext("a")
                .expectNext("r")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        var fruitsFluxFlatMapAsync = fluxAndMonoServices.fruitsFluxFlatMapAsync();
        StepVerifier.create(fruitsFluxFlatMapAsync)
                .expectNextCount(21)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMap() {
        var fruitMonoFlatMap = fluxAndMonoServices.fruitMonoFlatMap();
        StepVerifier.create(fruitMonoFlatMap)
                .expectNext((List<String>) List.of("M", "a", "n", "g", "o"))
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap() {
        var fruitsFluxConcatMap = fluxAndMonoServices.fruitsFluxConcatMap();
        StepVerifier.create(fruitsFluxConcatMap)
                .expectNext("A")
                .expectNext("p")
                .expectNext("p")
                .expectNext("l")
                .expectNext("e")
                .expectNext("B")
                .expectNext("a")
                .expectNext("n")
                .expectNext("a")
                .expectNext("n")
                .expectNext("a")
                .expectNext("O")
                .expectNext("r")
                .expectNext("a")
                .expectNext("n")
                .expectNext("g")
                .expectNext("e")
                .expectNext("P")
                .expectNext("e")
                .expectNext("a")
                .expectNext("r")
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMapMany() {
        var fruitMonoFlatMapMany = fluxAndMonoServices.fruitMonoFlatMapMany();
        StepVerifier.create(fruitMonoFlatMapMany)
                .expectNext("M")
                .expectNext("a")
                .expectNext("n")
                .expectNext("g")
                .expectNext("o")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {
        var fruitsFluxTransform = fluxAndMonoServices.fruitsFluxTransform(5);

        StepVerifier.create(fruitsFluxTransform)
                .expectNext("Banana")
                .expectNext("Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {
        var fruitsFluxTransformDefaultIfEmpty = fluxAndMonoServices.fruitsFluxTransformDefaultIfEmpty(10);

        StepVerifier.create(fruitsFluxTransformDefaultIfEmpty)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        var fruitsFluxTransformSwitchIfEmpty = fluxAndMonoServices.fruitsFluxTransformSwitchIfEmpty(10);

        StepVerifier.create(fruitsFluxTransformSwitchIfEmpty)
                .expectNext("Bananarama")
                .verifyComplete();
    }
}