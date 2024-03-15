package uk.ac.ucl.model;

public class Test {
    public static void main(String[] args) {
        DataLoader loader = new DataLoader("data/allergies100.csv");
        DataFrame frame = loader.getFrame();
        System.out.println("Column count: " + frame.getColCount());
        System.out.println("Row count: " + frame.getRowCount());
        System.out.println("Column names: " + frame.getColumnNames());
        System.out.println("Value at 0,0: " + frame.getValue("CODE", 0));
    }
}
