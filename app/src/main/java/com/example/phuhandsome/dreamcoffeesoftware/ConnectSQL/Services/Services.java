package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Services;

public class Services {
    private int ID_Services;
    private String Servicename;
    private String Describe;

    public Services(int ID_Services, String servicename, String describe) {
        this.ID_Services = ID_Services;
        Servicename = servicename;
        Describe = describe;
    }

    public int getID_Services() {
        return ID_Services;
    }

    public void setID_Services(int ID_Services) {
        this.ID_Services = ID_Services;
    }

    public String getServicename() {
        return Servicename;
    }

    public void setServicename(String servicename) {
        Servicename = servicename;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }
}
