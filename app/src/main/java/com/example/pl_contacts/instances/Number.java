package com.example.pl_contacts.instances;

public class Number {
    private String type;
    private String Number;

    public Number(String type, String number) {
        this.type = type;
        Number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
