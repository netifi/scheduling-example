package io.netifi.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ThreadLocalRandom;

public class PrintTenRandomNumbersInAnotherThread2 {
  public static void main(String... args) {
    Flux<Integer> observable = Flux.range(0, 10)
                                    .doOnNext(
                                        i ->
                                            System.out.println(
                                                "count - "
                                                    + i
                                                    + " - "
                                                    + Thread.currentThread()
                                                    + " - number: "
                                                    + ThreadLocalRandom.current().nextLong()));
    observable
        .subscribeOn(Schedulers.parallel())
        .blockLast();
  }
}
