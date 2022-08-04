package book.service;

import org.store.book.Book;
import org.store.book.OwnerDTO;
import org.store.book.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static data.sample.BookOwner.getBookOwner;
import static data.sample.BookSamples.dataBook;
import static data.sample.BookSamples.sampleReadyBook;
import static data.sample.UserSample.sampleReadyUser;
import static data.sample.UserSample.userData;

public class UserDashboard {
    private UserDashboard(){

    }
    public static void userDashboardRun(){
        boolean exit = false;
        try(Scanner scanner = new Scanner(System.in)) {
            Map<String, User> users = userData(sampleReadyUser());
            Map<Integer, OwnerDTO> owner =  getBookOwner();
            Map<String, List<Book>> bookStore = dataBook(sampleReadyBook());
            Map<String,String> request= new HashMap<>();
            User userLogin ;
            try {
                while (!exit){
                    System.out.println("Please Choose the following options given below");
                    System.out.println("L :   Choose L for Login      R :    Choose R for Register ");
                    System.out.println("E :    choose E for  Exit ");
                    String option = scanner.next();
                    if (option.equalsIgnoreCase("L")) {
                        UserLogin data = new UserLogin();
                        String userEmail;
                        try {
                            userEmail = data.userLogin(users,request);
                        }catch (Exception e){
                            continue;
                        }
                        userLogin = users.get(userEmail);
                        BookApp app = new BookApp();
                        app.bookAppDashboard(userLogin,owner,request,bookStore);
                    } else if (option.equalsIgnoreCase("R")) {
                        UserRegister reg = new UserRegister();
                        User registeredUser= reg.userRegistration(users);
                        System.out.println("Congratulations you have successfully registered in System With UserId please keep this userId with you for further process " + registeredUser.getUserId());
                        users.put(registeredUser.getEmail(),registeredUser);
                        System.out.println("enter y");
                        String decide = scanner.next();
                        if(users.containsKey(registeredUser.getEmail())) {
                            if (decide.equalsIgnoreCase("y")) {
                                userLogin = users.get(registeredUser.getEmail());
                                BookApp app = new BookApp();
                                app.bookAppDashboard(userLogin,owner,request,bookStore);
                            }else{
                                continue;
                            }
                        }
                    }else if(option.equalsIgnoreCase("E")) {
                        exit = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
