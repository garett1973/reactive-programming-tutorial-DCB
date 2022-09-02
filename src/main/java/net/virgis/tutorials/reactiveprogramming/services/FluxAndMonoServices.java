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
