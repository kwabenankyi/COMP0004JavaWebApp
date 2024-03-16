package uk.ac.ucl.model;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.xml.crypto.Data;

public class Model
{
  private DataFrame dataFrame;
  private ArrayList<String> patientNames;
  private void initialiseDF() {
    DataLoader dataLoader = new DataLoader("data/patients100.csv");
    this.dataFrame = dataLoader.getFrame();
  }
  // The example code in this class should be replaced by your Model class code.
  // The data should be stored in a suitable data structure.
  private void setPatientNames() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    ArrayList<String> firstnames = dataFrame.getColumnAsList("FIRST");
    ArrayList<String> lastnames = dataFrame.getColumnAsList("LAST");
    ArrayList<String> patientNames = new ArrayList<>();
    for (int i = 0; i < firstnames.size(); i++) {
      patientNames.add(lastnames.get(i) + ", " + firstnames.get(i));
    }
    patientNames.sort(String.CASE_INSENSITIVE_ORDER);
    this.patientNames = patientNames;
  }
  public List<String> getPatientNames() {
    if (this.patientNames == null) {
      this.setPatientNames();
    }
    return patientNames;
  }
  private int searchInColumn(String columnName, String keyword) {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    int count = 0;
    do {
      if (this.dataFrame.getValue(columnName, count).equals(keyword)) {
        return count;
      }
      count++;
    } while (count < this.dataFrame.getRowCount());
    return -1;
  }
  private HashMap<String,String> createDict(String patientID) {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    int rownum = searchInColumn("ID", patientID);
    if (rownum == -1) {
      return null;
    }
    HashMap<String,String> patientProfile = new HashMap<>();
    int i;
    String col;
    for (i=0; i<this.dataFrame.getColCount(); i++) {
      col = this.dataFrame.getColumnNames().get(i);
      patientProfile.put(col, this.dataFrame.getValue(col, rownum));
    }
    return patientProfile;
  }
  public HashMap<String,String> getPatientProfile(String patientID) {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    return createDict(patientID);
  }
  public String getPatientID(String name) {
      if (this.dataFrame == null) {
        this.initialiseDF();
      }
      String[] names = name.split(" ");
      int rownum = searchInColumn("FIRST", names[0]);
      if (rownum == -1) {
        return null;
      }
      if (this.dataFrame.getValue("LAST", rownum).equals(names[1])) {
        return this.dataFrame.getValue("ID", rownum);
      }
      return null;
  }

  public List<String> getPatientIDs() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    return this.dataFrame.getColumnAsList("ID");
  }

  public List<String> searchFor(String keyword)
  {
    ArrayList<String> searchResults = new ArrayList<>();
    if (this.patientNames == null) {
      this.getPatientNames();
    }
    for (String name : this.patientNames) {
      if ((name.toLowerCase()).contains(keyword.toLowerCase())) {
        searchResults.add(name);
      }
    }
    if (searchResults.isEmpty()) {
      searchResults.add("No results found");
    }
    return searchResults;
  }
}
