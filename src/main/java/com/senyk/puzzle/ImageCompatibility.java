package com.senyk.puzzle;

import java.awt.*;
import java.util.stream.IntStream;

public class ImageCompatibility {

  public static int countCompatibilityOfImages(int[] arr1, int[] arr2){

    if (arr1.length != arr2.length)
      return 0;
    ImageCompatibility imageCompatibility = new ImageCompatibility();
    return (int) IntStream.range(0, arr1.length)
        .filter(i -> imageCompatibility.isEquals(arr1[i], arr2[i]))
        .count();
  }
  private boolean isEquals(int a, int b){

//    Color colorA = new Color(a);
//    Color colorB = new Color(b);
//
//    int aRed = colorA.getRed();
//    int aBlue = colorA.getBlue();
//    int aGreen = colorA.getGreen();
//    int bRed = colorB.getRed();
//    int bBlue = colorB.getBlue();
//    int bGreen = colorB.getGreen();
//    return Math.abs(aRed - bRed) < 15 &&
//        Math.abs(aGreen - bGreen) < 15 &&
//        Math.abs(aBlue - bBlue) < 15;
//    return colorA.equals(colorB);
    return a==b;
  }
}
