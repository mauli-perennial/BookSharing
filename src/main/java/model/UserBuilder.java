package model;

public  class UserBuilder{
    final int userId;
    final  String firstName;
    final String lastName;
    final int mobileNumber;
    private  String bioData = "";
    final String email;
    final  String password;

    public UserBuilder(int userId,String firstName, String lastName,int mobileNumber,String email,String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
    }
    public UserBuilder bioData(String  bioData) {
        this.bioData = bioData;
        return this;
    }
    public User build() {
        return new User(this);
    }
}