package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill;

public class DetailsBill {
    private int numTable;
    private String drinksName;
    private int numberOrder;

    public DetailsBill(int numTable, String drinksName, int numberOrder) {
        this.numTable = numTable;
        this.drinksName = drinksName;
        this.numberOrder = numberOrder;
    }

    public int getNumTable() {
        return numTable;
    }

    public void setNumTable(int numTable) {
        this.numTable = numTable;
    }

    public String getDrinksName() {
        return drinksName;
    }

    public void setDrinksName(String drinksName) {
        this.drinksName = drinksName;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }
}
