package book.service;

import org.store.book.User;

public class BookService {
    public static User createNewUser(int userid, String name, String lastName, int mobile, String userInformation, String emailAddress, String passwordLogin) {
        User user = new User();
        user.setUserId(userid);
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setMobileNumber(mobile);
        user.setUserInformation(userInformation);
        user.setEmail(emailAddress);
        user.setPassword(passwordLogin);
        System.out.println(user);
        return user;
    }
    public static String maskLeft(String password, int length, char mask) {
        if (length <= 0) {
            return password;
        }
        length = Math.min(length, password.length());
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < length; index++) {
            sb.append(mask);
        }
        sb.append(password.substring(length));
        return sb.toString();
    }

}
