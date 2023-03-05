package br.com.extractorquery.threads;

import java.util.concurrent.TimeUnit;

public class Printer implements Runnable{

  private final int num;

  public Printer(int num) {
    this.num = num;
  }

  @Override
  public void run() {
    System.out.printf("%s inicio: %d%n", Thread.currentThread().getName(), num);
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.printf("%s finalizou", Thread.currentThread().getName() + "\n");

  }


}

