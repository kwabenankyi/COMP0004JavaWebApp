package uk.ac.ucl.model;
import java.util.ArrayList;
import java.util.HashMap;

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
    public HashMap<String,String> mapOneColToAnother(String keys, String values) {
        HashMap<String,String> map = new HashMap<>();
        ArrayList<String> keyList = getColumnAsList(keys);
        ArrayList<String> valueList = getColumnAsList(values);
        for (int i = 0; i < keyList.size(); i++) {
            map.put(keyList.get(i), valueList.get(i));
        }
        return map;
    }
    public void sortAllByOneColumn(String columnName) {
        String temp;
        boolean swapped = false;
        //bubble sort one column, but keep the rows together
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                for (int i = 0; i < column.getSize() - 1; i++) {//one less row to sort each time
                    swapped = false;
                    for (int j = 0; j < column.getSize() - i - 1; j++) {
                        if (column.getRowValue(j).compareTo(column.getRowValue(j + 1)) > 0) {
                            for (Column col : columns) {
                                temp = col.getRowValue(j);
                                col.setRowValue(j, col.getRowValue(j + 1));
                                col.setRowValue(j + 1, temp);
                            }
                            swapped = true;
                        }
                    }
                    if (!swapped) {//if no swaps, then the list is sorted
                        break;
                    }
                }
            }
        }
    }
}
