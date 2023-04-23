package com.anthonyf.smsautoreply2.model;

public class Contact {

    private final String contactId;
    private final String displayName;
    private final String phoneNumber;
    private boolean isSelected;

    public Contact(String contactId, String displayName, String phoneNumber, boolean isSelected) {
        this.contactId = contactId;
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
        this.isSelected = isSelected;
    }

    public String getContactId() { return contactId; }
    public String getDisplayName() { return displayName; }
    public String getPhoneNumber() { return phoneNumber; }
    public boolean isSelected() { return isSelected; }
    public void select() { this.isSelected = true; }
    public void unselect() { this.isSelected = false; }


}
