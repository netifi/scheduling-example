package io.netifi.jdk;

import java.util.concurrent.ThreadLocalRandom;

public class PrintTenRandomNumbers {
    public static void main(String... args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(
                "count - "
                    + i
                    + " - "
                    + Thread.currentThread()
                    + " - number: "
                    + ThreadLocalRandom.current().nextLong());
        }
    }
}
