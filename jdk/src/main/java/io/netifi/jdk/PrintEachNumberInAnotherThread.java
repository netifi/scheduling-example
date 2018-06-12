package io.netifi.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadLocalRandom;

public class PrintEachNumberInAnotherThread {
  public static void main(String... args) {
    List<ForkJoinTask> taskList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      int _i = i;
      ForkJoinTask<?> submit =
          ForkJoinPool.commonPool()
              .submit(
                  () -> {
                    System.out.println(
                        "count - "
                            + _i
                            + " - "
                            + Thread.currentThread()
                            + " - number: "
                            + ThreadLocalRandom.current().nextLong());
                  });
      taskList.add(submit);
    }

    taskList.forEach(ForkJoinTask::join);
  }
}
