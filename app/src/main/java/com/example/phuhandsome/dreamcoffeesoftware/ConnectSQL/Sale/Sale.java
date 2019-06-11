package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale;

public class Sale {
    private int ID_Sale;
    private String Datesale;
    private int PercentSale;

    public Sale(int ID_Sale, String datesale, int percentSale) {
        this.ID_Sale = ID_Sale;
        Datesale = datesale;
        PercentSale = percentSale;
    }

    public void setID_Sale(int ID_Sale) {
        this.ID_Sale = ID_Sale;
    }

    public int getID_Sale() {
        return ID_Sale;
    }

    public void setDatesale(String datesale) {
        this.Datesale = datesale;
    }

    public String getDatesale() {
        return Datesale;
    }

    public void setPercentSale(int percentSale) {
        this.PercentSale = percentSale;
    }

    public int getPercentSale() {
        return PercentSale;
    }
}
