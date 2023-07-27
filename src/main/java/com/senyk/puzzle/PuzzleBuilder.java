package com.senyk.puzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class PuzzleBuilder {

  public static LinkedList<Image> buildRow(LinkedList<Image> images, int columns){

    LinkedList<Image> row = new LinkedList<>();
    boolean wayOfBuildIsRight = true;
    PuzzleBuilder p = new PuzzleBuilder();
    while (columns!=0) {
      if (row.isEmpty()){
        row.addFirst(images.get(0));
        images.remove(0);
      }
      else if (wayOfBuildIsRight){
        List<Double> percents = new ArrayList<>();
        for (Image image : images) {
          percents.add(ImageCompatibility.percentCompatibilityOfImages(row.getLast().getRIGHT(), image.getLEFT()));
        }
        int maxPosition = p.getMaxPosition(percents);

        if (maxPosition == -1) {
          wayOfBuildIsRight = false;
        }
        else {
          row.addFirst(images.get(maxPosition));
          images.remove(maxPosition);
        }
      }
      else {
        List<Double> percents = new ArrayList<>();
        for (Image image : images) {
          percents.add(ImageCompatibility.percentCompatibilityOfImages(row.getFirst().getLEFT(), image.getRIGHT()));
        }
        int maxPosition = p.getMaxPosition(percents);
        row.addFirst(images.get(maxPosition));
        images.remove(maxPosition);
      }
      columns--;
    }

    return row;
  }

  public static LinkedList<LinkedList<Image>> buildPuzzle(LinkedList<LinkedList<Image>> images, int rows){

    LinkedList<LinkedList<Image>> puzzle = new LinkedList<>();
    boolean wayOfBuildIsBottom = true;
    PuzzleBuilder p = new PuzzleBuilder();
    while (rows!=0) {
      if (puzzle.isEmpty()){
        puzzle.addFirst((LinkedList<Image>) images.get(0));
        images.remove(0);
      }
      else if (wayOfBuildIsBottom){
        List<Double> percents = new ArrayList<>();
        for (List<Image> image : images) {
          percents.add(ImageCompatibility.percentCompatibilityOfImages(
              puzzle.getLast().stream()
                  .flatMapToInt(myClass -> IntStream.of(myClass.getBOTTOM()))
                  .toArray(),
              image.stream()
              .flatMapToInt(myClass -> IntStream.of(myClass.getTOP()))
              .toArray()));
        }
        int maxPosition = p.getMaxPosition(percents);

        if (maxPosition == -1) {
          wayOfBuildIsBottom = false;
        }
        else {
          puzzle.addFirst((LinkedList<Image>) images.get(maxPosition));
          images.remove(maxPosition);
        }
      }
      else {
        List<Double> percents = new ArrayList<>();
        for (List<Image> image : images) {
          percents.add(ImageCompatibility.percentCompatibilityOfImages(
              puzzle.getLast().stream()
                  .flatMapToInt(myClass -> IntStream.of(myClass.getTOP()))
                  .toArray(),
              image.stream()
                  .flatMapToInt(myClass -> IntStream.of(myClass.getBOTTOM()))
                  .toArray()));
        }
        int maxPosition = p.getMaxPosition(percents);
        puzzle.addFirst((LinkedList<Image>) images.get(maxPosition));
        images.remove(maxPosition);
        }

      rows--;

    }

    return puzzle;
  }

  private int getMaxPosition(List<Double> percents){

    double maxPercent = 0;
    int maxPosition = -1;

    for (int i = 0; i < percents.size(); i++) {
      double currentPercent = percents.get(i);
      if (currentPercent > maxPercent) {
        maxPercent = currentPercent;
        maxPosition = i;
      }
    }

    return maxPosition;
  }
}
