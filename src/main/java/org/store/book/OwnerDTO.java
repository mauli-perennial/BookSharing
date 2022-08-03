package org.store.book;

public class OwnerDTO {
private String ownerName;
private String ownerEmail;

    public OwnerDTO(String ownerName, String ownerEmail) {
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "ownerName='" + ownerName + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                '}';
    }
}
