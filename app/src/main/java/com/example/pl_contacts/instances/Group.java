package com.example.pl_contacts.instances;

import java.util.List;

public class Group {
    private String name;
    private int contactNumbers;
    private List<Contact> contactList;

    public Group(String name, int contactNumbers) {
        this.name = name;
        this.contactNumbers = contactNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(int contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
