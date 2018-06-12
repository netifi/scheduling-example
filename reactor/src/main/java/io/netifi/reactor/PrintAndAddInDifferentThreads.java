package io.netifi.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ThreadLocalRandom;

public class PrintAndAddInDifferentThreads {
  public static void main(String... args) {
    Flux.range(0, 10)
        .publishOn(Schedulers.parallel())
        .map(
            i -> {
              long rnd = ThreadLocalRandom.current().nextLong();
              System.out.println(
                  Thread.currentThread() + " - count - " + i + " - number: " + rnd);
              return rnd;
            })
        .publishOn(Schedulers.parallel())
        .reduce(
            (integer, integer2) -> {
              long total = integer + integer2;
              System.out.println(Thread.currentThread() + " - current total: " + total);
              return total;
            })
        .doOnNext(total -> System.out.println(Thread.currentThread() + " - total -> " + total))
        .block();
  }
}
