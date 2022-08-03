package book.service;

import custom_exceptions.UserHandlingException;
import org.store.book.User;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static validation.ValidationForBookAndUser.*;

public class UserRegister {
    public  User userRegistration( Map<String,User> users) throws UserHandlingException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the email");
        String  emailAddress = scanner.next();
        try{
            checkEmail(emailAddress);
        }catch (Exception e){
           e.printStackTrace();
        }
        System.out.println("Enter the password ");
        String passwordRegister = scanner.next();
        Random random = new Random();
        int userid = random.nextInt();
        System.out.println("Enter your first name");
        String name = scanner.next();
        System.out.println("enter your lastname");
        String lastName = scanner.next();
        System.out.println("enter your mobile number");
        int mobile = scanner.nextInt();
        System.out.println("Enter the User information");
        String userInformation = scanner.next();
        return createNewUser(userid, name, lastName, mobile, userInformation,emailAddress, passwordRegister);
    }

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
}
