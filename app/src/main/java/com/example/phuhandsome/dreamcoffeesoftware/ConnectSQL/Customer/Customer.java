package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Customer;

public class Customer {
    private int ID_Customer, ID_Account;
    private String Customername, Birthofdate, sex, Phonenumber, Email, CusAddress;
    private int C_coint;

    public Customer(int ID_Customer, int ID_Account, String customername, String birthofdate, String sex, String phonenumber, String email, String cusAddress, int c_coint) {
        this.ID_Customer = ID_Customer;
        this.ID_Account = ID_Account;
        Customername = customername;
        Birthofdate = birthofdate;
        this.sex = sex;
        Phonenumber = phonenumber;
        Email = email;
        CusAddress = cusAddress;
        C_coint = c_coint;
    }

    public int getID_Customer() {
        return ID_Customer;
    }

    public void setID_Customer(int ID_Customer) {
        this.ID_Customer = ID_Customer;
    }

    public int getID_Account() {
        return ID_Account;
    }

    public void setID_Account(int ID_Account) {
        this.ID_Account = ID_Account;
    }

    public String getCustomername() {
        return Customername;
    }

    public void setCustomername(String customername) {
        Customername = customername;
    }

    public String getBirthofdate() {
        return Birthofdate;
    }

    public void setBirthofdate(String birthofdate) {
        Birthofdate = birthofdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCusAddress() {
        return CusAddress;
    }

    public void setCusAddress(String cusAddress) {
        CusAddress = cusAddress;
    }

    public int getC_coint() {
        return C_coint;
    }

    public void setC_coint(int c_coint) {
        C_coint = c_coint;
    }
}
