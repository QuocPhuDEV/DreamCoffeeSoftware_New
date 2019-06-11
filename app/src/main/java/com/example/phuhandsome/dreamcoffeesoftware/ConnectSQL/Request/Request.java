package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Request;

public class Request {
    private int Numtable;
    private String Request;

    public Request(int numtable, String request) {
        Numtable = numtable;
        Request = request;
    }

    public int getNumtable() {
        return Numtable;
    }

    public void setNumtable(int numtable) {
        Numtable = numtable;
    }

    public String getRequest() {
        return Request;
    }

    public void setRequest(String request) {
        Request = request;
    }
}
