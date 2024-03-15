package uk.ac.ucl.model;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.xml.crypto.Data;

public class Model
{
  private DataFrame dataFrame;
  private ArrayList<String> patientNames;
  // The example code in this class should be replaced by your Model class code.
  // The data should be stored in a suitable data structure.
  public List<String> getPatientNames() {
    DataLoader dataLoader = new DataLoader("data/patients100.csv");
    DataFrame dataFrame = dataLoader.getFrame();
    ArrayList<String> firstnames = dataFrame.getColumnAsList("FIRST");
    ArrayList<String> lastnames = dataFrame.getColumnAsList("LAST");
    ArrayList<String> patientNames = new ArrayList<>();
    for (int i = 0; i < firstnames.size(); i++) {
      patientNames.add(firstnames.get(i) + " " + lastnames.get(i));
    }
    this.patientNames = patientNames;
    return patientNames;
  }

  // This method illustrates how to read csv data from a file.
  // The data files are stored in the root directory of the project (the directory your project is in),
  // in the directory named data.
  public List<String> readFile(String fileName)
  {
    List<String> data = new ArrayList<>();

    try (Reader reader = new FileReader(fileName);
         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT))
    {
      for (CSVRecord csvRecord : csvParser)
      {
        // The first row of the file contains the column headers, so is not actual data.
        data.add(csvRecord.get(0));
      }
    } catch (IOException e)
    {
      e.printStackTrace();
    }
    return data;
  }

  // This also returns dummy data. The real version should use the keyword parameter to search
  // the data and return a list of matching items.
  public List<String> searchFor(String keyword)
  {
    ArrayList<String> searchResults = new ArrayList<>();
    if (this.patientNames == null) {
      this.getPatientNames();
    }
    for (String name : this.patientNames) {
      if ((name.toLowerCase()).contains(keyword)) {
        searchResults.add(name);
      }
    }
    if (searchResults.isEmpty()) {
      searchResults.add("No results found");
    }
    return searchResults;
  }
}
