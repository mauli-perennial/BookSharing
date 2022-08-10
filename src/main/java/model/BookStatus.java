package model;

public enum BookStatus {
    TAKEN("Taken"), AVAILABLE("Available");

    private String name;

    BookStatus() {
    }

    public String getName() {
        return name;
    }

    BookStatus(String name) {
        this.name = name;
    }
}
