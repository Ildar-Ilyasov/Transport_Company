package com.example.transport_company;

public class RouteTable {
    private String wherefrom;
    private String wher;
    private String deliverytime;
    private String coordwherefrom;
    private String coordwhere;
    public RouteTable(String wherefrom, String wher, String deliverytime,String coordwherefrom,String coordwhere) {
        this.wherefrom = wherefrom;
        this.wher = wher;
        this.deliverytime = deliverytime;
        this.coordwherefrom = coordwherefrom;
        this.coordwhere = coordwhere;
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
    public String getDeliverytime() {
        return deliverytime;
    }
    public void setDeliverytime(String deliverytime) {
        this.deliverytime = deliverytime;
    }
    public String getCoordwherefrom() {
        return coordwherefrom;
    }
    public void setCoordwherefrom(String coordwherefrom) {
        this.coordwherefrom = coordwherefrom;
    }
    public String getCoordwhere() {
        return coordwhere;
    }
    public void setCoordwhere(String coordwhere) {
        this.coordwhere = coordwhere;
    }
}