package net.virgis.tutorials.reactiveprogramming.services;

import org.assertj.core.groups.Tuple;
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

    @Test
    void fruitsFluxConcat() {
        var fruitsFluxConcat = fluxAndMonoServices.fruitsFluxConcat();

        StepVerifier.create(fruitsFluxConcat)
                .expectNext("Apple")
                .expectNext("Banana")
                .expectNext("Orange")
                .expectNext("Pear")
                .expectNext("Carrot", "Potato", "Broccoli", "Cauliflower")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWith() {
        var fruitsFluxConcatWith = fluxAndMonoServices.fruitsFluxConcatWith();

        StepVerifier.create(fruitsFluxConcatWith)
                .expectNext("Apple")
                .expectNext("Banana")
                .expectNext("Orange")
                .expectNext("Pear")
                .expectNext("Carrot", "Potato", "Broccoli", "Cauliflower")
                .verifyComplete();
    }

    @Test
    void fruitsMonoConcatWith() {
        var fruitsMonoConcatWith = fluxAndMonoServices.fruitsMonoConcatWith();

        StepVerifier.create(fruitsMonoConcatWith)
                .expectNext("Apple")
                .expectNext("Carrot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitsFluxMerge = fluxAndMonoServices.fruitsFluxMerge();

        StepVerifier.create(fruitsFluxMerge)
                .expectNext("Apple")
                .expectNext("Carrot")
                .expectNext("Banana")
                .expectNext("Potato")
                .expectNext("Orange")
                .expectNext("Broccoli")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitsFluxMergeWith = fluxAndMonoServices.fruitsFluxMergeWith();

        StepVerifier.create(fruitsFluxMergeWith)
                .expectNext("Apple")
                .expectNext("Carrot")
                .expectNext("Banana")
                .expectNext("Potato")
                .expectNext("Orange")
                .expectNext("Broccoli")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWithSequential() {
        var fruitsFluxMergeWithSequential = fluxAndMonoServices.fruitsFluxMergeWithSequential();

        StepVerifier.create(fruitsFluxMergeWithSequential)
                .expectNext("Apple")
                .expectNext("Banana")
                .expectNext("Orange")
                .expectNext("Carrot")
                .expectNext("Potato")
                .expectNext("Broccoli")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitsFluxZip = fluxAndMonoServices.fruitsFluxZip();

        StepVerifier.create(fruitsFluxZip)
                .expectNext("Apple - Carrot")
                .expectNext("Banana - Potato")
                .expectNext("Orange - Broccoli")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var fruitsFluxZipWith = fluxAndMonoServices.fruitsFluxZipWith();

        StepVerifier.create(fruitsFluxZipWith)
                .expectNext("Apple - Carrot")
                .expectNext("Banana - Potato")
                .expectNext("Orange - Broccoli")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var fruitsFluxZipTuple = fluxAndMonoServices.fruitsFluxZipTuple();

        StepVerifier.create(fruitsFluxZipTuple)
                .expectNext("Apple - Carrot - Celery")
                .expectNext( "Banana - Potato - Lettuce")
                .expectNext("Orange - Broccoli - Radish")
                .verifyComplete();
    }

    @Test
    void fruitsMonoZipWith() {
        var fruitsMonoZipWith = fluxAndMonoServices.fruitsMonoZipWith();

        StepVerifier.create(fruitsMonoZipWith)
                .expectNext("Apple - Carrot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterDoOn() {
        var fruitsFluxFilterDoOn = fluxAndMonoServices.fruitsFluxFilterDoOn(4);

        StepVerifier.create(fruitsFluxFilterDoOn)
                .expectNext("Apple")
                .expectNext("Banana")
                .expectNext("Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorReturn() {
        var fruitsFluxOnErrorReturn = fluxAndMonoServices.fruitsFluxOnErrorReturn(5);

        StepVerifier.create(fruitsFluxOnErrorReturn)
                .expectNext("Apple")
                .expectNext("Default fruit")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorReturnWith() {
        var fruitsFluxOnErrorReturnWith = fluxAndMonoServices.fruitsFluxOnErrorReturnWith(5);

        StepVerifier.create(fruitsFluxOnErrorReturnWith)
                .expectNext("Apple", "Banana", "Orange", "Pear", "Default fruit")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorContinue() {
        var fruitsFluxOnErrorContinue = fluxAndMonoServices.fruitsFluxOnErrorContinue(5);

        StepVerifier.create(fruitsFluxOnErrorContinue)
                .expectNext("Apple", "Pear")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorMap() {
        var fruitsFluxOnErrorMap = fluxAndMonoServices.fruitsFluxOnErrorMap(5);

        StepVerifier.create(fruitsFluxOnErrorMap)
                .expectNext("Apple")
                .expectError(IllegalStateException.class)
                .verify();
    }

    @Test
    void fruitsFluxOnError() {
        var fruitsFluxOnError = fluxAndMonoServices.fruitsFluxOnError(5);

        StepVerifier.create(fruitsFluxOnError)
                .expectNext("Apple")
                .expectError(RuntimeException.class)
                .verify();
    }
}