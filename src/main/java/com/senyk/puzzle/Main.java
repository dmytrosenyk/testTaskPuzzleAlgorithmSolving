package com.senyk.puzzle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

public class Main {

  public static void main(String[] args) throws Exception {

    String imgPath = "src/main/resources/f1.jpg";
    int rows =4;
    int columns =4;

    LinkedList<LinkedList<Image>> puzzles =new LinkedList<>();
    BufferedImage image = ReadImg.readFile(imgPath);
    LinkedList<BufferedImage> bufferedImages = DivisionImage.divisionImageIntoParts(image,rows,columns);
    LinkedList<Image> images = bufferedImages.stream()
        .map(Image::new)
        .collect(Collectors.toCollection(LinkedList::new));
    for (int i = 0; i < images.size(); i++) {
      images.get(i).setIndex(i);
    }
    Collections.shuffle(images);

    for (int i = 0; i < rows; i++) {
      puzzles.add(PuzzleBuilder.buildRow(images,columns));
      puzzles.forEach(System.out::println);
      System.out.println();
    }
    List<Image> result = new java.util.ArrayList<>(PuzzleBuilder.buildPuzzle(puzzles, rows)
        .stream()
        .flatMap(LinkedList::stream)
        .toList());

    for (int i = 0; i < result.size(); i++) {
      String name = "src/main/resources/sub-img-"+i+".jpg";
      File outputfile = new File(name);
      ImageIO.write(result.get(i).getImage(), "jpg", outputfile);
    }
  }
}
