package com.example.sudokusolver;

public class districtWiseData {
    String district;
    String active,recovered,deceased,confirmed,notes;

    public districtWiseData(String district, String active, String recovered, String deceased, String confirmed, String notes) {
        this.district = district;
        this.active = active;
        this.recovered = recovered;
        this.deceased = deceased;
        this.confirmed = confirmed;
        this.notes=notes;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
