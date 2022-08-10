package user.app;

import exceptions.EmailFormatException;
import exceptions.DuplicateUserException;
import exceptions.UserNotFoundException;
import exceptions.WrongCredentialsException;
import main.application.TestBookApp;
import model.User;
import model.UserBuilder;

import java.util.*;
import java.util.logging.Logger;

import static book_user_validator.ValidationForBookAndUser.*;

public class UserManager {
    public static final Logger log = Logger.getLogger(String.valueOf(TestBookApp.class));
    static ResourceBundle bundle = ResourceBundle.getBundle("menu", Locale.CANADA_FRENCH);
    public User userRegistration() throws EmailFormatException, DuplicateUserException {
        Scanner scanner = new Scanner(System.in);
        log.info(bundle.getString("email"));
        String emailAddress = scanner.next();
        try {
            checkEmail(emailAddress);
            checkIfUserExist(emailAddress);
        } catch (EmailFormatException e) {
            throw new EmailFormatException("invalid mail format");
        } catch (DuplicateUserException e) {
            throw new DuplicateUserException(" user already present with this email");
        }
        log.info("Enter the password ");
        String passwordRegister = scanner.next();

        Random random = new Random();
        int userid = random.nextInt();
        log.info(bundle.getString("firstName"));
        String name = scanner.next();
        log.info(bundle.getString("lastName"));
        String lastName = scanner.next();
        log.info(bundle.getString("mobile"));
        int mobile = scanner.nextInt();
        log.info(bundle.getString("biodata"));
        String userInformation = scanner.next();
        return newUser(userid, name, lastName, mobile, userInformation, emailAddress, passwordRegister);
    }

    public  User newUser(int userid, String name, String lastName, int mobile, String userInformation, String emailAddress, String passwordLogin) {
        return new UserBuilder(userid,name,lastName,mobile,emailAddress,passwordLogin).bioData(userInformation).build();
    }

    public User userLogin(String email , String password, Map<String, String> request, Map<String, User> users) throws WrongCredentialsException, UserNotFoundException {
        User loggedUser = users.get(email);
        if(loggedUser != null) {
            if (loggedUser.getEmail().equalsIgnoreCase(email) && loggedUser.getPassword().equalsIgnoreCase(password)) {
                log.info(request.get(email));
            } else {
                throw new UserNotFoundException(" user not found");
            }
        }else {
            throw new WrongCredentialsException(" user not present with this email id");
        }
        User myUser = new User(new UserBuilder(loggedUser.getUserId(),loggedUser.getFirstName(),loggedUser.getLastName(),loggedUser.getMobileNumber(),loggedUser.getEmail(), " " ));
        return myUser;
    }
}
