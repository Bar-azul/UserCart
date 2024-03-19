package com.example.ex2app;

public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private int milkQuantity;
    private int eggsQuantity;

    public User(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getMilkQuantity() {
        return milkQuantity;
    }

    public void setMilkQuantity(int milkQuantity) {
        this.milkQuantity = milkQuantity;
    }

    public int getEggsQuantity() {
        return eggsQuantity;
    }

    public void setEggsQuantity(int eggsQuantity) {
        this.eggsQuantity = eggsQuantity;
    }
}
