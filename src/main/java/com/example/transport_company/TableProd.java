package com.example.transport_company;

public class TableProd {
    private String name;
    private String weight;
    private String wherefrom;
    private String wher;
    private String company;
    private String driver;
    private String selectedDate;
    private String deliveryDate;
    private String currentTime;
    public TableProd(String name, String weight, String wherefrom,String wher,String company,String driver,String selectedDate,String deliveryDate, String currentTime) {
        this.name = name;
        this.weight = weight;
        this.wherefrom = wherefrom;
        this.wher = wher;
        this.company = company;
        this.driver = driver;
        this.selectedDate = selectedDate;
        this.deliveryDate = deliveryDate;
        this.currentTime = currentTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getWherefrom() {
        return wherefrom;
    }
    public void setWherefrom(String wherefrom) {
        this.wherefrom = wherefrom;
    }
    public String getWher() {
        return wher;
    }
    public void setWher(String wher) {
        this.wher = wher;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String driver) {
        this.company = company;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getSelectedDate() {
        return selectedDate;
    }
    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getCurrentTime() {
        return currentTime;
    }
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}