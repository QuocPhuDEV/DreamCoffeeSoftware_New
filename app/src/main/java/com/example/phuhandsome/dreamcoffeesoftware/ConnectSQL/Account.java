package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL;

public class Account {
    private int id_Account;
    private String userName, passWord, permission;

    public Account(int id_Account, String userName, String passWord, String permission) {
        this.id_Account = id_Account;
        this.userName = userName;
        this.passWord = passWord;
        this.permission = permission;
    }

    public void setId_Account(int id_Account) {
        this.id_Account = id_Account;
    }

    public int getId_Account() {
        return id_Account;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
