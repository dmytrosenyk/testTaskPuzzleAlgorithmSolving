package com.senyk.puzzle;

import java.util.stream.IntStream;

public class ImageCompatibility {

  public static double percentCompatibilityOfImages(int[] arr1, int[] arr2){

    if (arr1.length != arr2.length)
      return 0;

    long countCompatibility = IntStream.range(0, arr1.length)
        .filter(i -> arr1[i] == arr2[i])
        .count();

    return (countCompatibility * 100.0) / arr1.length;
  }
}
