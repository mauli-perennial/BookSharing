package book_user_validator;

import exceptions.BooKNameException;
import exceptions.EmailFormatException;
import exceptions.DuplicateUserException;
import exceptions.PasswordFormatException;
import model.User;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dummydata.UserSample.sampleReadyUser;
import static dummydata.UserSample.userData;
import static model.Book.sdf;

public class ValidationForBookAndUser {
    private static final String PASSWORD_PATTERN = "^[A-Za-z]\\w{5,29}$";
    private static final String BOOK_NAME = "^[a-zA-Z0-9]*$";

    private static final String NAME = "^[a-zA-Z]*$";
    private static final String EMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    private static final Pattern patternBook = Pattern.compile(BOOK_NAME);

    private static final Pattern patternName = Pattern.compile(NAME);
    private static final Pattern emailPattern = Pattern.compile(EMAIL);

    public static Date convertDate(String date) throws ParseException {
        return sdf.parse(date);
    }


     public static void validateBookName(String name) throws BooKNameException {
        Matcher matcher = patternBook.matcher(name);
        if (!matcher.matches()) {
            throw new BooKNameException("Invalid book name format");
        }
    }


     private static void validateName(String name) throws BooKNameException {
        Matcher matcher = patternName.matcher(name);
        if (matcher.matches()) {
            throw new BooKNameException(" wrong book name format");
        }
    }


     public static void checkIfUserExist(String email) throws DuplicateUserException {
        Map<String, User> users = userData(sampleReadyUser());
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                throw new DuplicateUserException(" Duplicate user found");
            }
        }
    }


     private static boolean isValidEmailAddress(String email) {
      Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }


    private static boolean isValid(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    public static void checkEmail(String email) throws EmailFormatException {
        if (!isValidEmailAddress(email)) {
            throw new EmailFormatException("email format wrong");
        }
    }


    public static void checkPassword(String password) throws PasswordFormatException {
        if (isValid(password)) {
            throw new PasswordFormatException("password format wrong");
        }
    }

}
