package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks;

public class Menu {
    private int ID_Drink, ID_Type;
    private String Drinks;
    private int Price, Inventory;

    public Menu(int ID_Drink, int ID_Type, String Drinks, int Price, int Inventory) {
        this.ID_Drink = ID_Drink;
        this.ID_Type = ID_Type;
        this.Drinks = Drinks;
        this.Price = Price;
        this.Inventory = Inventory;

    }


    public void setID_Drink(int ID_Drink) {
        this.ID_Drink = ID_Drink;
    }

    public int getID_Drink() {
        return ID_Drink;
    }


    public void setID_Type(int ID_Type) {
        this.ID_Type = ID_Type;
    }

    public int getID_Type() {
        return ID_Type;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getPrice() {
        return Price;
    }

    public void setInventory(int Inventory) {
        this.Inventory = Inventory;
    }

    public int getInventory() {
        return Inventory;
    }

    public void setDrinks(String Drinks) {
        this.Drinks = Drinks;
    }

    public String getDrinks() {
        return Drinks;
    }
}
