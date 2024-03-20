package uk.ac.ucl.model;

import java.util.*;
import java.time.LocalDate;
import java.time.Period;

public class Model
{
  private DataFrame dataFrame;
  private ArrayList<String> patientNames;
  private final String filename;
  public Model(String filename) {
    this.filename = filename;
    this.initialiseDF(filename);
  }
  private void initialiseDF(String filename) {
    DataLoader dataLoader = new DataLoader(filename);
    this.dataFrame = dataLoader.getFrame();
    this.setPatientNames();
  }
  // The example code in this class should be replaced by your Model class code.
  // The data should be stored in a suitable data structure.
  private void setPatientNames() {
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
    return createDict(patientID);
  }
  public List<String> getPatientIDs() {
    return this.dataFrame.getColumnAsList("ID");
  }
  public ArrayList<ArrayList<String>> searchFor(String keyword) {
    // returns list of patient names and list of IDs
    ArrayList<ArrayList<String>> returnList = new ArrayList<>();
    ArrayList<String> searchResults = new ArrayList<>();
    ArrayList<String> searchResultsIDs = new ArrayList<>();
    int i;
    String[] words = keyword.split(" ");
    int len = words.length;
    int count = 0;
    for (i = 0; i < this.getNumPatients(); i++) {
      count = 0;
      for (String word : words) {
        if ((this.patientNames.get(i).toLowerCase()).contains(word.toLowerCase())) {
          count++;
        }
      }
      if (count== len) {
        searchResults.add(this.patientNames.get(i));
        searchResultsIDs.add(this.dataFrame.getValue("ID", i));
      }
    }
    returnList.add(searchResults);
    returnList.add(searchResultsIDs);
    return returnList;
  }
  public List<String> getCities() {
    return this.dataFrame.getColumnAsList("CITY");
  }
  public HashMap<String,ArrayList<String>> splitPatientsByCity() {
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
    this.patientNames.clear();
    this.setPatientNames();
    HashMap<String, String> idsToName = new HashMap<>();
    for (int i = 0; i < this.patientNames.size(); i++) {
      idsToName.put(this.dataFrame.getValue("ID", i), this.patientNames.get(i));
    }
    return idsToName;
  }
  public ArrayList<String> getDeceased() {
    ArrayList<String> deceased = new ArrayList<>();
    for (int i = 0; i < this.dataFrame.getRowCount(); i++) {
      if (!(this.dataFrame.getValue("DEATHDATE", i).isEmpty())) {
        deceased.add(this.dataFrame.getValue("ID", i));
      }
    }
    return deceased;
  }
  public void sort(String columnName) {
    this.dataFrame.sortAllByOneColumn(columnName);
  }
  public int calcAge(String dob) {
    return Period.between(LocalDate.parse(dob), LocalDate.now()).getYears();
  }
  public int getNumPatients() {
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
    HashMap<String,ArrayList<String>> ages = new HashMap<>();
    ArrayList<String> ageGroups = getAgeGroups();
    for (String group : ageGroups) {
      if (!(group.equals("Deceased"))) {
        ages.put(group, new ArrayList<>());
      } else {
        ages.put(group, getDeceased());
      }
    }
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
      HashMap<String,String> addresses = new HashMap<>();
      for (int i = 0; i < this.dataFrame.getRowCount(); i++) {
        addresses.put(this.dataFrame.getValue("ID", i), this.dataFrame.getValue("ADDRESS", i) + ", " + this.dataFrame.getValue("CITY", i) + ", " + this.dataFrame.getValue("STATE", i) + ", " + this.dataFrame.getValue("ZIP", i));
      }
      return addresses;
  }
  public HashMap<String,String> getIDsToDOB() {
    return this.dataFrame.mapOneColToAnother("ID", "BIRTHDATE");
  }
  public int getNumOfMales() {
    int males = 0;
    for (String gender : this.dataFrame.getColumnAsList("GENDER")) {
      if (gender.equals("M")) {
        males++;
      }
    }
    return males;
  }
  public int getNumOfDrivers() {
    int drivers = 0;
    for (String driver : this.dataFrame.getColumnAsList("DRIVERS")) {
      if (!(driver.isEmpty())) {
        drivers++;
      }
    }
    return drivers;
  }
  public double getAverageAge() {
    int totalAge = 0;
    int count = 0;
    for (String dob : this.dataFrame.getColumnAsList("BIRTHDATE")) {
      totalAge += calcAge(dob);
      count++;
    }
    return Math.round((double) totalAge / count);
  }
  public int getNumEthnicities() {
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
}
