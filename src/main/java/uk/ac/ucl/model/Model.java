package uk.ac.ucl.model;

import java.util.*;
import java.time.LocalDate;
import java.time.Period;

public class Model
{
  protected DataFrame dataFrame;
  protected String filename;
  public Model(String filename) {
    this.filename = filename;
    this.initialiseDF(filename);
  }
  protected void initialiseDF(String filename) {
    DataLoader dataLoader = new DataLoader(filename);
    this.dataFrame = dataLoader.getFrame();
  }
  protected int searchInColumn(String columnName, String keyword) {
    int count = 0;
    do {
      if (this.dataFrame.getValue(columnName, count).equals(keyword)) {
        return count;
      }
      count++;
    } while (count < this.dataFrame.getRowCount());
    return -1;
  }
  public void sort(String columnName) {
    this.dataFrame.sortAllByOneColumn(columnName);
  }
  protected int getNumRows() {
    return this.dataFrame.getRowCount();
  }
}
