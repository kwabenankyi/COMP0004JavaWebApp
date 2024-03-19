package uk.ac.ucl.model;

import jdk.jfr.Percentage;

import java.util.*;

import java.time.LocalDate;
import java.time.Period;

import javax.xml.crypto.Data;

public class Model
{
  private DataFrame dataFrame;
  private ArrayList<String> patientNames;
  private void initialiseDF() {
    DataLoader dataLoader = new DataLoader("data/patients1000.csv");
    this.dataFrame = dataLoader.getFrame();
    this.setPatientNames();
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
    this.patientNames = patientNames;
  }
  public List<String> getPatientNames() {
    this.setPatientNames();
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
      String[] names = name.split(", ");
      int row = searchInColumn("FIRST", names[0]);
      if (row == -1) {
        return null;
      }
      if (this.dataFrame.getValue("LAST", row).equals(names[1])) {
        return this.dataFrame.getValue("ID", row);
      }
      return null;
  }

  public List<String> getPatientIDs() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    return this.dataFrame.getColumnAsList("ID");
  }

  public ArrayList<ArrayList<String>> searchFor(String keyword)
  {
    ArrayList<ArrayList<String>> returnList = new ArrayList<>();
    ArrayList<String> searchResults = new ArrayList<>();
    ArrayList<String> searchResultsIDs = new ArrayList<>();
    if (this.patientNames == null) {
      this.getPatientNames();
    }
    int i;
    for (i = 0; i < this.patientNames.size(); i++) {
      if ((this.patientNames.get(i).toLowerCase()).contains(keyword.toLowerCase())) {
        searchResults.add(this.patientNames.get(i));
        searchResultsIDs.add(this.dataFrame.getValue("ID", i));
      }
    }

    if (searchResults.isEmpty()) {
      searchResults.add("No results found");
      searchResultsIDs.add("null");
    }
    returnList.add(searchResults);
    returnList.add(searchResultsIDs);
    return returnList;
  }
  public List<String> getAddresses() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    return this.dataFrame.getColumnAsList("ADDRESS");
  }
  public List<String> getCities() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    return this.dataFrame.getColumnAsList("CITY");
  }

  public HashMap<String,ArrayList<String>> splitPatientsByCity() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    HashMap<String,ArrayList<String>> cityPatients = new HashMap<>();
    ArrayList<String> newCity;
    ArrayList<String> cities = this.dataFrame.getColumnAsList("CITY");
    ArrayList<String> patientIDs = this.dataFrame.getColumnAsList("ID");
    for (int i = 0; i < cities.size(); i++) {
      try {
        cityPatients.get(cities.get(i)).add(patientIDs.get(i));
      } catch (NullPointerException e) {
        newCity = new ArrayList<>();
        newCity.add(patientIDs.get(i));
        cityPatients.put(cities.get(i), newCity);
      }
    }
    return cityPatients;
  }
  public HashMap<String, String> getIDsToNames() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    this.patientNames.clear();
    this.setPatientNames();
    HashMap<String, String> idsToName = new HashMap<>();
    for (int i = 0; i < this.patientNames.size(); i++) {
      idsToName.put(this.dataFrame.getValue("ID", i), this.patientNames.get(i));
    }
    return idsToName;
  }
  public ArrayList<String> getDeceased() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    ArrayList<String> deceased = new ArrayList<>();
    for (int i = 0; i < this.dataFrame.getRowCount(); i++) {
      if (!(this.dataFrame.getValue("DEATHDATE", i).isEmpty())) {
        deceased.add(this.dataFrame.getValue("ID", i));
      }
    }
    return deceased;
  }
  public void sort(String columnName) {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    this.dataFrame.sortAllByOneColumn(columnName);
  }
  public int calcAge(String dob) {
    return Period.between(LocalDate.parse(dob), LocalDate.now()).getYears();
  }
  public int getNumPatients() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    return this.dataFrame.getRowCount();
  }
  public ArrayList<String> getAgeGroups() {
    ArrayList<String> ageGroups = new ArrayList<>();
    ageGroups.add("0-17");
    ageGroups.add("18-25");
    ageGroups.add("26-40");
    ageGroups.add("41-60");
    ageGroups.add("61-80");
    ageGroups.add("81+");
    ageGroups.add("Deceased");
    return ageGroups;
  }
  public HashMap<String,ArrayList<String>> getIDsToAges() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    HashMap<String,ArrayList<String>> ages = new HashMap<>();
    ages.put("0-17", new ArrayList<>());
    ages.put("18-25", new ArrayList<>());
    ages.put("26-40", new ArrayList<>());
    ages.put("41-60", new ArrayList<>());
    ages.put("61-80", new ArrayList<>());
    ages.put("81+", new ArrayList<>());
    ages.put("Deceased", this.getDeceased());
    ArrayList<String> alive = new ArrayList<>(this.getPatientIDs());
    alive.removeAll(ages.get("Deceased")); // list of ids of patients who are alive
    int rownum;
    for (String patientID : alive) {
      rownum = searchInColumn("ID", patientID);
      switch (calcAge(this.dataFrame.getValue("BIRTHDATE", rownum))) {
        case 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17:
          ages.get("0-17").add(patientID);
          break;
        case 18,19,20,21,22,23,24,25:
          ages.get("18-25").add(patientID);
          break;
        case 26,27,28,29,30,31,32,33,34,35,36,37,38,39,40:
          ages.get("26-40").add(patientID);
          break;
        case 41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60:
          ages.get("41-60").add(patientID);
          break;
        case 61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80:
          ages.get("61-80").add(patientID);
          break;
        default:
          ages.get("81+").add(patientID);
      }
    }
    return ages;
  }
  public HashMap<String,String> getIDsToAddresses() {
      if (this.dataFrame == null) {
        this.initialiseDF();
      }
      HashMap<String,String> addresses = new HashMap<>();
      for (int i = 0; i < this.dataFrame.getRowCount(); i++) {
        addresses.put(this.dataFrame.getValue("ID", i), this.dataFrame.getValue("ADDRESS", i) + ", " + this.dataFrame.getValue("CITY", i) + ", " + this.dataFrame.getValue("STATE", i) + ", " + this.dataFrame.getValue("ZIP", i));
      }
      return addresses;
  }
  public HashMap<String,String> getIDsToDOB() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    return this.dataFrame.mapOneColToAnother("ID", "BIRTHDATE");
  }
  public int getNumOfMales() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    int males = 0;
    for (String gender : this.dataFrame.getColumnAsList("GENDER")) {
      if (gender.equals("M")) {
        males++;
      }
    }
    return males;
  }
  public int getNumOfDrivers() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    int drivers = 0;
    for (String driver : this.dataFrame.getColumnAsList("DRIVERS")) {
      if (!(driver.isEmpty())) {
        drivers++;
      }
    }
    return drivers;
  }
  public double getAverageAge() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    int totalAge = 0;
    int count = 0;
    for (String dob : this.dataFrame.getColumnAsList("BIRTHDATE")) {
      totalAge += calcAge(dob);
      count++;
    }
    return Math.round((double) totalAge / count);
  }
  public int getNumEthnicities() {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    Set<String> ethnic = new HashSet<>();
    int count = 0;
    for (String e : this.dataFrame.getColumnAsList("ETHNICITY")) {
      if (!(ethnic.contains(e))) {
        count++;
        ethnic.add(e);
      }
    }
    return count;
  }
  public ArrayList<String> getIDsForList(List<String> list) {
    if (this.dataFrame == null) {
      this.initialiseDF();
    }
    this.getPatientNames();
    ArrayList<String> resultList = new ArrayList<>();
    for (String name : list) {
      resultList.add(getPatientID(name));
    }
    return resultList;
  }
}
