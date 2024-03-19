package uk.ac.ucl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private DataFrame frame;
    private String fileName;
    private ArrayList<String> columnNames;
    public DataLoader(String fileName) {
        this.fileName = fileName;
        this.columnNames = new ArrayList<>();
        this.frame = this.readFile();
    }
    private DataFrame readFile() {
        DataFrame df = new DataFrame();
        try (Reader reader = new FileReader(fileName);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            boolean firstrow = true;
            for (CSVRecord csvRecord : csvParser) {
                if (firstrow) {
                    for (String header : csvRecord) {
                        columnNames.add(header);
                        df.addColumn(new Column(header));
                    }
                    firstrow = false;
                } else {
                    for (int i = 0; i < columnNames.size(); i++) {
                        df.addValue(columnNames.get(i), csvRecord.get(i));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return df;
    }
    public DataFrame getFrame() {
        return frame;
    }
}
