package io.netifi.jdk;

import java.util.concurrent.*;

public class PrintAndAddInDifferentThreads {
  public static void main(String... args) throws Exception {
    BlockingQueue<Long> queue = new LinkedBlockingQueue<>();
    CountDownLatch latch = new CountDownLatch(10);
    ForkJoinPool.commonPool()
        .submit(
            () -> {
              for (int i = 0; i < 10; i++) {
                long rnd = ThreadLocalRandom.current().nextLong();
                System.out.println(
                    Thread.currentThread() + " - count - " + i + " - number: " + rnd);
                queue.add(rnd);
              }
            });

    ForkJoinPool.commonPool()
        .submit(
            () -> {
              long total = 0;
              for (; ; ) {
                Long poll = queue.poll();
                if (poll != null) {
                  latch.countDown();
                  total += poll;
                  System.out.println(Thread.currentThread() + " - current total: " + total);
                  if (latch.getCount() == 0) {
                    break;
                  }
                }
              }

              System.out.println(Thread.currentThread() + " - total -> " + total);
            });

    latch.await();
  }
}
