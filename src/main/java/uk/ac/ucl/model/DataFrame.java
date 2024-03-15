package uk.ac.ucl.model;
import java.util.ArrayList;

public class DataFrame {
    private ArrayList<Column> columns;
    public DataFrame() {
        this.columns = new ArrayList<>();
    }
    public int getColCount() {
        return columns.size();
    }
    public int getRowCount() {
        return columns.getFirst().getSize();
    }
    public void addColumn(Column column) {
        columns.add(column);
    }
    public ArrayList<String> getColumnNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Column column : columns) {
            names.add(column.getName());
        }
        return names;
    }
    public ArrayList<String> getColumnAsList(String columnName) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                return column.toList();
            }
        }
        return null;
    }
    public String getValue(String columnName, int rowIndex) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                return column.getRowValue(rowIndex);
            }
        }
        return null;
    }
    public void putValue(String columnName, int rowIndex, String value) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                column.setRowValue(rowIndex, value);
            }
        }
    }
    public void addValue(String columnName, String value) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                column.addRowValue(value);
            }
        }
    }
}
