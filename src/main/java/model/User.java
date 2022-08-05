package model;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private int mobileNumber;
    private String userInformation;
    private String email;
    private String password;

    public User() {

    }

    public User(int userId, String firstName, String lastName, int mobileNumber, String userInformation, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.userInformation = userInformation;
        this.email = email;
        this.password = password;

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

    public String getUserInformation() {
        return userInformation;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", userInformation='" + userInformation + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
