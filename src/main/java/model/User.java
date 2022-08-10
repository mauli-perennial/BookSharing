package model;

import java.util.Objects;

public final class User {
    private final int userId;
    private final  String firstName;
    private final String lastName;
    private  final int mobileNumber;
    private  String bioData;
    private final String email;
    private final  String password;


    public User(UserBuilder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.mobileNumber = builder.mobileNumber;
        this.email = builder.email;
        this.password = builder.password;

    }

    public int getUserId() {
        return userId;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public int getMobileNumber() {
        return mobileNumber;
    }

    public String getBioData() {
        return bioData;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() && getMobileNumber() == user.getMobileNumber() && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getBioData(), user.getBioData()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getFirstName(), getLastName(), getMobileNumber(), getBioData(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", biodata ='" + bioData + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
