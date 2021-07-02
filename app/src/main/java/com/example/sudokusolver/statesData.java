package com.example.sudokusolver;

public class statesData {
    String state;
    String active,recovered,deceased,total;

    public statesData(String state, String active, String recovered, String deceased, String total) {
        this.state = state;
        this.active = active;
        this.recovered = recovered;
        this.deceased = deceased;
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
