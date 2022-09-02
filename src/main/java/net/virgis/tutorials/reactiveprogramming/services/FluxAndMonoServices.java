package net.virgis.tutorials.reactiveprogramming.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoServices {

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange", "Pear")).log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange", "Pear"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int length) {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange", "Pear"))
                .filter(s -> s.length() > length)
                .log();
    }

    public Flux<String> fruitsFluxFilterMap(int length) {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange", "Pear"))
                .filter(s -> s.length() > length)
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange", "Pear"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange", "Pear"))
                .flatMap(s -> Flux.just(s.split(""))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))) // delay each element by a random amount of time
                .log();
    }

    public Flux<String> fruitsFluxConcatMap() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange", "Pear"))
                .concatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                )) // delay each element by a random amount of time
                .log();
    }

    public Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxTransform(int number) {

        Function<Flux<String>,Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Apple", "Banana", "Orange", "Pear"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int number) {

            Function<Flux<String>,Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

            return Flux.fromIterable(List.of("Apple", "Banana", "Orange", "Pear"))
                    .transform(filterData)
                    .defaultIfEmpty("Default")
                    .log();
    }

    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number) {

            Function<Flux<String>,Flux<String>> filterData = data -> data.filter(s -> s.length() >= number);

            return Flux.fromIterable(List.of("Apple", "Banana", "Orange", "Pear"))
                    .transform(filterData)
                    .switchIfEmpty(Flux.just("Bananarama", "Applejack", "Orangeade"))
                    .transform(filterData)
                    .log();
    }

    public Flux<String> fruitsFluxConcat() {
        var fruits = Flux.just("Apple", "Banana", "Orange", "Pear");
        var vegetables = Flux.just("Carrot", "Potato", "Broccoli", "Cauliflower");

        return Flux.concat(fruits, vegetables)
                .log();
    }

    public Flux<String> fruitsFluxConcatWith() {
        var fruits = Flux.just("Apple", "Banana", "Orange", "Pear");
        var vegetables = Flux.just("Carrot", "Potato", "Broccoli", "Cauliflower");

        return fruits.concatWith(vegetables)
                .log();
    }

    public Flux<String> fruitsMonoConcatWith() {
        var fruits = Mono.just("Apple");
        var vegetables = Mono.just("Carrot");
        return fruits.concatWith(vegetables)
                .log();
    }

    public Flux<String> fruitsFluxMerge() {
        var fruits = Flux.just("Apple", "Banana", "Orange")
        .delayElements(Duration.ofMillis(50));
        var vegetables = Flux.just("Carrot", "Potato", "Broccoli")
        .delayElements(Duration.ofMillis(75));

        return Flux.merge(fruits, vegetables)
                .log();
    }

    public Flux<String> fruitsFluxMergeWith() {
        var fruits = Flux.just("Apple", "Banana", "Orange")
                .delayElements(Duration.ofMillis(50));
        var vegetables = Flux.just("Carrot", "Potato", "Broccoli")
                .delayElements(Duration.ofMillis(75));

        return fruits.mergeWith(vegetables)
                .log();
    }

    public Flux<String> fruitsFluxMergeWithSequential() {
        var fruits = Flux.just("Apple", "Banana", "Orange")
                .delayElements(Duration.ofMillis(50));
        var vegetables = Flux.just("Carrot", "Potato", "Broccoli")
                .delayElements(Duration.ofMillis(75));

        return Flux.mergeSequential(fruits, vegetables)
                .log();
    }
    public Flux<String> fruitsFluxZip() {
        var fruits = Flux.just("Apple", "Banana", "Orange");
        var vegetables = Flux.just("Carrot", "Potato", "Broccoli");

        return Flux.zip(fruits, vegetables, (first, second) -> first + " - " + second)
                .log();
    }

    public Flux<String> fruitsFluxZipWith() {
        var fruits = Flux.just("Apple", "Banana", "Orange");
        var vegetables = Flux.just("Carrot", "Potato", "Broccoli");

        return fruits.zipWith(vegetables, (first, second) -> first + " - " + second)
                .log();
    }

    public Flux<String> fruitsFluxZipTuple() {
        var fruits = Flux.just("Apple", "Banana", "Orange");
        var vegetables = Flux.just("Carrot", "Potato", "Broccoli");
        var plants = Flux.just("Celery", "Lettuce", "Radish");

        return Flux.zip(fruits, vegetables, plants)
                .map(objects -> objects.getT1() + " - " + objects.getT2() + " - " + objects.getT3())
                .log();
    }

    public Mono<String> fruitsMonoZipWith() {
        var fruits = Mono.just("Apple");
        var vegetables = Mono.just("Carrot");

        return fruits.zipWith(vegetables, (first, second) -> first + " - " + second)
                .log();
    }

    public Mono<String> fruitMono() {
        return Mono.just("Mango").log();
    }

    public Mono<List<String>> fruitMonoFlatMap() {
        return Mono.just("Mango")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public static void main(String[] args) {

        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();
        fluxAndMonoServices.fruitsFlux().subscribe(s -> {
            System.out.println("Flux -> s = " + s);
        });

        fluxAndMonoServices.fruitMono().subscribe(s -> {
            System.out.println("Mono -> s = " + s);
        });
    }
}
