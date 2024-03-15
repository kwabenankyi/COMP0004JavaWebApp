package uk.ac.ucl.model;

import java.util.ArrayList;

public class Column {
    private String name;
    private ArrayList<String> rows;
    public Column(String name) {
        this.name = name;
        this.rows = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public int getSize() {
        return rows.size();
    }
    public String getRowValue(int index) {
        return rows.get(index);
    }
    public void addRowValue(String value) {
        rows.add(value);
    }
    public void setRowValue(int index, String value) {
        rows.set(index, value);
    }
    public ArrayList<String> toList() {
        return rows;
    }
}
