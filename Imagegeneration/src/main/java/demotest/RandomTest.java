/**
 * Copyright (c) 2022, 59store. All rights reserved.
 */
package demotest;

import java.util.Random;

/**
 *
 * @author <a href="mailto:chenll@umi.cash">chenll</a>
 * @version 1.0 2022/3/25
 * @since 1.0
 */
public class RandomTest {
  public static void main(String[] args) {
    Random random = new Random();
    /*for (int j = 0; j < 10; j++) {
      int i = random.nextInt(10);
      System.out.println("ints = " + i);
    }*/

    int max=10;
    int min=0;

    for (int i = 0; i < 10; i++) {
      int s = random.nextInt(max)%(max-min+1) + min;
      if (i != 0) {
        System.out.println(s);
      }
    }

  }
}
