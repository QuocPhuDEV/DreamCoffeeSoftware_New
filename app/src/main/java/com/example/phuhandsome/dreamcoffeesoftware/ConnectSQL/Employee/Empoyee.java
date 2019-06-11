package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee;

public class Empoyee {
    private int ID_Employee, ID_Account, ID_Position;
    private String Employeename, Birthofdate, sex, Phonenumber, Email, CusAddress;

    public Empoyee(int ID_Employee, int ID_Account, int ID_Position, String employeename, String birthofdate, String sex, String phonenumber, String email, String cusAddress) {
        this.ID_Employee = ID_Employee;
        this.ID_Account = ID_Account;
        this.ID_Position = ID_Position;
        Employeename = employeename;
        Birthofdate = birthofdate;
        this.sex = sex;
        Phonenumber = phonenumber;
        Email = email;
        CusAddress = cusAddress;
    }

    public int getID_Employee() {
        return ID_Employee;
    }

    public void setID_Employee(int ID_Employee) {
        this.ID_Employee = ID_Employee;
    }

    public int getID_Account() {
        return ID_Account;
    }

    public void setID_Account(int ID_Account) {
        this.ID_Account = ID_Account;
    }

    public int getID_Position() {
        return ID_Position;
    }

    public void setID_Position(int ID_Position) {
        this.ID_Position = ID_Position;
    }

    public String getEmployeename() {
        return Employeename;
    }

    public void setEmployeename(String employeename) {
        Employeename = employeename;
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
}
