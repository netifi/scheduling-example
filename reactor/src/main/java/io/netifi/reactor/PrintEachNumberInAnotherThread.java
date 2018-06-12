package io.netifi.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ThreadLocalRandom;

public class PrintEachNumberInAnotherThread {
  public static void main(String... args) {
    Flux.range(0, 10)
        .flatMap(
            i ->
                Mono.fromRunnable(
                        () ->
                            System.out.println(
                                "count - "
                                    + i
                                    + " - "
                                    + Thread.currentThread()
                                    + " - number: "
                                    + ThreadLocalRandom.current().nextLong()))
                    .subscribeOn(Schedulers.parallel()))
        .blockLast();
  }
}
