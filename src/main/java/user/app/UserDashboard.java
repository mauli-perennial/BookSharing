package user.app;

import book.app.BookAppDashboard;
import exceptions.EmailFormatException;
import exceptions.DuplicateUserException;
import main.application.TestBookApp;
import model.Book;
import model.User;

import java.util.*;
import java.util.logging.Logger;

import static dummydata.BookOwner.getBookOwner;
import static dummydata.BookSamples.dataBook;
import static dummydata.BookSamples.sampleReadyBook;
import static dummydata.UserSample.sampleReadyUser;
import static dummydata.UserSample.userData;

public class UserDashboard {
    public static final Logger log = Logger.getLogger(String.valueOf(TestBookApp.class));
    static ResourceBundle bundle = ResourceBundle.getBundle("menu", Locale.CANADA_FRENCH);
    public static void userDashboardRun() throws DuplicateUserException {

        boolean exit = false;
        Map<String, User> users = userData(sampleReadyUser());
        Map<Integer, User> owner = getBookOwner();
        Map<String, List<Book>> bookStore = dataBook(sampleReadyBook());
        Map<String, String> request = new HashMap<>();
        User userLogin;
        try (Scanner scanner = new Scanner(System.in)) {

            while (!exit) {
                log.info("Please Choose the following options given below");
                String menu = String.format("%s %n %s %n %s " , bundle.getString("login"),bundle.getString("register"),bundle.getString("logout"));
                log.info(menu);
                String option = scanner.next();

                switch (option) {

                    case "L":
                       log.info(bundle.getString("email"));
                        String userEmail = scanner.next();
                        log.info(bundle.getString("password"));
                        String password = scanner.next();
                        try {
                            userLogin = UserManager.userLogin(userEmail,password,request,users);
                        } catch (Exception e) {
                            continue;
                        }
                        BookAppDashboard app = new BookAppDashboard();
                        app.dashboard(userLogin, owner, request, bookStore);
                        break;

                    case "R":

                        User registeredUser;
                        try {
                            registeredUser = UserManager.userRegistration();
                        }  catch (EmailFormatException e) {
                            throw new RuntimeException(e);
                        }
                        log.info(bundle.getString("success") + registeredUser.getUserId());
                        users.put(registeredUser.getEmail(), registeredUser);
                        log.info("enter y");
                        String decide = scanner.next();
                        if (users.containsKey(registeredUser.getEmail()) && decide.equalsIgnoreCase("y")) {
                                userLogin = users.get(registeredUser.getEmail());
                                BookAppDashboard appReg = new BookAppDashboard();
                                appReg.dashboard(userLogin, owner, request, bookStore);
                        }
                        break;

                    case "E":
                        log.info(bundle.getString("exits"));
                        exit = true;
                        break;

                    default:
                        log.info(" please enter right option");
                        break;
                }
            }
        }
    }
}