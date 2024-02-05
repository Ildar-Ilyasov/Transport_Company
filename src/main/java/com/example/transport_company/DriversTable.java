package com.example.transport_company;

public class DriversTable {
    private String name;
    private String company;
    private String transport;
    private String location;

    public DriversTable(String name, String company, String transport,String location) {
        this.name = name;
        this.company = company;
        this.transport = transport;
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getTransport() {
        return transport;
    }
    public void setTransport(String transport) {
        this.transport = transport;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}