package book.service;

import custom_exceptions.UserHandlingException;
import org.store.book.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static validation.ValidationForBookAndUser.maskLeft;

public class UserLogin {
    public String userLogin(Map<String,User> users,Map<String,String> request) throws UserHandlingException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the user Email");
        String email = scanner.next();
        User user = users.get(email);
        boolean login = false;
        if (user != null) {
            System.out.println("enter the password");
            String maksPassword = scanner.next();
            String password = maskLeft(maksPassword,maksPassword.length(),'*');
            System.out.println(" Password " + password);
            if (user.getEmail().equals(email) && user.getPassword().equals(maksPassword)) {
                login = true;
                if(request.containsKey(email)){
                    System.out.println(request.get(email));
                }
            } else {
                throw new UserHandlingException("wrong credentials");
            }
        } else {
            throw new UserHandlingException("user not present with this email");
        }
        if (!login) {
            throw new UserHandlingException("Please provide right credentials");
        }
        return email;
    }
}
