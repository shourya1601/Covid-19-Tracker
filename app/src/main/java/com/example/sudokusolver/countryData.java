package com.example.sudokusolver;

public class countryData {
    String country;
    String active,recovered,deceased,cases,tests;

    public countryData(String country, String active, String recovered, String deceased, String cases,String tests) {
        this.country = country;
        this.active = active;
        this.recovered = recovered;
        this.deceased = deceased;
        this.cases = cases;
        this.tests = tests;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }
}
