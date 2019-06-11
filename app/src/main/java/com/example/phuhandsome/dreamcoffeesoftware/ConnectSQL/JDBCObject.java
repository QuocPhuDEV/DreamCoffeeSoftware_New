package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL;

public class JDBCObject {
    private String sServerName, sUserId, sPwd, sDatabases, sClass, sPrort;

    public JDBCObject(String ServerName, String UserId, String Pwd, String Databases, String Prort) {
        this.sServerName = ServerName;
        this.sUserId = UserId;
        this.sPwd = Pwd;
        this.sDatabases = Databases;
        this.sPrort = Prort;
        this.sClass = "net.sourceforge.jtds.jdbc.Driver";
    }

    public void setsPrort(String sPrort) {
        this.sPrort = sPrort;
    }

    public String getsPrort() {
        return sPrort;
    }


    public void setsServerName(String sServerName) {
        this.sServerName = sServerName;
    }

    public String getsServerName() {
        return sServerName;
    }


    public void setsUserId(String sUserId) {
        this.sUserId = sUserId;
    }

    public String getsUserId() {
        return sUserId;
    }

    public void setsPwd(String pwd) {
        this.sPwd = pwd;
    }

    public String getsPwd() {
        return sPwd;
    }

    public void setsDatabases(String sDatabases) {
        this.sDatabases = sDatabases;
    }

    public String getsDatabases() {
        return sDatabases;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getsClass() {
        return sClass;
    }
}
