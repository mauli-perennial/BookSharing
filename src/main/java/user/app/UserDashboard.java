package user.app;

import book.app.BookAppDashboard;
import exceptions.UserException;
import model.Book;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static dummydata.BookOwner.getBookOwner;
import static dummydata.BookSamples.dataBook;
import static dummydata.BookSamples.sampleReadyBook;
import static dummydata.UserSample.sampleReadyUser;
import static dummydata.UserSample.userData;

public class UserDashboard {
    public static void userDashboardRun() throws UserException {

        boolean exit = false;
        Map<String, User> users = userData(sampleReadyUser());
        Map<Integer, User> owner = getBookOwner();
        Map<String, List<Book>> bookStore = dataBook(sampleReadyBook());
        Map<String, String> request = new HashMap<>();
        User userLogin;
        try (Scanner scanner = new Scanner(System.in)) {

            while (!exit) {

                System.out.println("Please Choose the following options given below");
                System.out.println("L :   Choose L for Login      R :    Choose R for Register ");
                System.out.println("E :    choose E for  Exit ");

                String option = scanner.next();

                switch (option) {

                    case "L":

                        UserLogin data = new UserLogin();
                        String userEmail;
                        try {
                            userEmail = data.userLogin(users, request);
                        } catch (Exception e) {
                            continue;
                        }
                        userLogin = users.get(userEmail);
                        BookAppDashboard app = new BookAppDashboard();
                        app.dashboard(userLogin, owner, request, bookStore);
                        break;

                    case "R":

                        UserRegister reg = new UserRegister();
                        User registeredUser;
                        try {
                            registeredUser = reg.userRegistration(users);
                        } catch (UserException e) {
                            throw new UserException(" user not registered");


                        }
                        System.out.println("Congratulations you have successfully registered in System With UserId please keep this userId with you for further process " + registeredUser.getUserId());
                        users.put(registeredUser.getEmail(), registeredUser);
                        System.out.println("enter y");
                        String decide = scanner.next();
                        if (users.containsKey(registeredUser.getEmail())) {
                            if (decide.equalsIgnoreCase("y")) {
                                userLogin = users.get(registeredUser.getEmail());
                                BookAppDashboard appReg = new BookAppDashboard();
                                appReg.dashboard(userLogin, owner, request, bookStore);
                            }
                        }
                        break;

                    case "E":
                        System.out.println(" exiting from app");
                        exit = true;
                        break;

                    default:
                        System.out.println(" please enter right option");
                        break;
                }
            }
        }
    }
}