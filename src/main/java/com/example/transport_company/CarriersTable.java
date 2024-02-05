package com.example.transport_company;

public class CarriersTable {
    private String name;
    private String term;
    private String capacity;
    private String typetransport;
    public CarriersTable(String name, String term, String capacity,String typetransport) {
        this.name = name;
        this.term = term;
        this.capacity = capacity;
        this.typetransport = typetransport;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public String getCapacity() {
        return capacity;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    public String getTypetransport() {
        return typetransport;
    }
    public void setTypetransport(String typetransport) {
        this.typetransport = typetransport;
    }
}