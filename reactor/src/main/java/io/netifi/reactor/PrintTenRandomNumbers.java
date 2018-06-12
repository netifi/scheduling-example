package io.netifi.reactor;

import reactor.core.publisher.Flux;

import java.util.concurrent.ThreadLocalRandom;

public class PrintTenRandomNumbers {
  public static void main(String... args) {
    Flux.range(0, 10)
        .doOnNext(
            i ->
                System.out.println(
                    "count - "
                        + i
                        + " - "
                        + Thread.currentThread()
                        + " - number: "
                        + ThreadLocalRandom.current().nextLong()))
        .subscribe();
  }
}
