package io.netifi.jdk;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

public class PrintTenRandomNumbersInAnotherThread {
  public static void main(String... args) {
    ForkJoinPool.commonPool()
        .submit(
            () -> {
              for (int i = 0; i < 10; i++) {
                System.out.println(
                    "count - "
                        + i
                        + " - "
                        + Thread.currentThread()
                        + " - number: "
                        + ThreadLocalRandom.current().nextLong());
              }
            })
        .join();
  }
}
