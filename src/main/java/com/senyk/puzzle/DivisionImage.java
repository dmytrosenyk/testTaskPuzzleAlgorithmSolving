package com.senyk.puzzle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DivisionImage {

  public static LinkedList<BufferedImage> divisionImageIntoParts(BufferedImage image, int rows, int columns){

    LinkedList<BufferedImage> pieces = new LinkedList<>();
    int imageWidth = image.getWidth();
    int imageHeight = image.getHeight();
    BufferedImage[] subImgs = new BufferedImage[rows * columns];

    int subImageWidth = imageWidth / columns;
    int subImageHeight = imageHeight / rows;

    int current_img = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        subImgs[current_img] = new BufferedImage(subImageWidth, subImageHeight, image.getType());
        Graphics2D img_creator = subImgs[current_img].createGraphics();

        int src_first_x = subImageWidth * j;
        int src_first_y = subImageHeight * i;

        int dst_corner_x = subImageWidth * j + subImageWidth;
        int dst_corner_y = subImageHeight * i + subImageHeight;

        img_creator.drawImage(image, 0, 0, subImageWidth, subImageHeight, src_first_x, src_first_y, dst_corner_x, dst_corner_y, null);
        current_img++;
      }
    }

    Collections.addAll(pieces, subImgs);

    return pieces;
  }
}
