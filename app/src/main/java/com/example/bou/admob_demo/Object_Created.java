package com.example.bou.admob_demo;

public class Object_Created {
    private String gmail;
    private int totalAddClicked;
    private String Key;

    public Object_Created(String gmail, int totalAddClicked, String key) {
        this.gmail = gmail;
        this.totalAddClicked = totalAddClicked;
        this.Key = key;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public int getTotalAddClicked() {
        return totalAddClicked;
    }

    public void setTotalAddClicked(int totalAddClicked) {
        this.totalAddClicked = totalAddClicked;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}

