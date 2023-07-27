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
    System.out.println(bufferedImages.size());
    Collections.shuffle(bufferedImages);
    LinkedList<Image> images = bufferedImages.stream()
        .map(Image::new)
        .collect(Collectors.toCollection(LinkedList::new));
    for (int i = 0; i < rows; i++) {
      puzzles.add(PuzzleBuilder.buildRow(images,columns));
    }
    List<Image> result = new java.util.ArrayList<>(PuzzleBuilder.buildPuzzle(puzzles, rows)
        .stream()
        .flatMap(LinkedList::stream)
        .toList());;
    Collections.reverse(result);
    for (int i = 0; i < result.size(); i++) {
      String name = "src/main/resources/sub-img-"+i+".jpg";
      File outputfile = new File(name);
      ImageIO.write(result.get(i).getImage(), "jpg", outputfile);
    }
//    for (int i = 0; i < rows; i++) {
//      for (int j = 0; j < columns; j++) {
//        String name = "src/main/resources/sub-img-"+i+"-"+j+".jpg";
//        File outputfile = new File(name);
//        ImageIO.write(result.get(i*(columns-1)+j).getImage(), "jpg", outputfile);
//      }
//    }
  }
}
