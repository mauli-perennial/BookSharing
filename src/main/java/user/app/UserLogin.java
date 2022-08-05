package user.app;

import exceptions.UserException;
import model.User;

import java.util.Map;
import java.util.Scanner;

import static book_user_validator.ValidationForBookAndUser.maskLeft;

public class UserLogin {
    public String userLogin(Map<String, User> users, Map<String, String> request) throws UserException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the user Email");
        String email = scanner.next();
        User user = users.get(email);
        boolean login = false;
        if (user != null) {
            System.out.println("enter the password");
            String maksPassword = scanner.next();
            String password = maskLeft(maksPassword, maksPassword.length(), '*');
            System.out.println(" Password " + password);
            if (user.getEmail().equals(email) && user.getPassword().equals(maksPassword)) {
                login = true;
                if (request.containsKey(email)) {
                    System.out.println(request.get(email));
                }
            } else {
                throw new UserException("wrong credentials");
            }
        } else {
            throw new UserException("user not present with this email");
        }
        if (!login) {
            throw new UserException("Please provide right credentials");
        }
        return email;
    }
}
